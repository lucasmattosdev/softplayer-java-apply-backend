package br.com.lucasmattos.softplayer.java.apply.backend.provider.interceptor;

import br.com.lucasmattos.softplayer.java.apply.backend.core.SessionApplication;
import br.com.lucasmattos.softplayer.java.apply.backend.entity.UsuarioEntity;
import br.com.lucasmattos.softplayer.java.apply.backend.service.UsuarioService;
import org.jboss.resteasy.util.Base64;

import javax.annotation.Priority;
import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.StringTokenizer;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationInterceptor implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Inject
    UsuarioService usuarioService;

    private static final String AUTHENTICATION_SCHEME = "BASIC";

    @Override
    public void filter(ContainerRequestContext req) {
        PermitAll permitAllMethod = resourceInfo.getResourceMethod().getAnnotation(PermitAll.class);
        PermitAll permitAllClass = resourceInfo.getResourceClass().getAnnotation(PermitAll.class);

        if (permitAllMethod == null && permitAllClass == null) {
            final String authorization = req.getHeaderString(HttpHeaders.AUTHORIZATION);
            if(authorization == null || authorization.isEmpty())
            {
                this.abortWithStatus(req, Response.Status.NOT_ACCEPTABLE);
                return;
            }

            StringTokenizer nomeSenha;
            try{
                nomeSenha = this.extractUsernamePasswordFromBasic(authorization);
            } catch (IOException e) {
                this.abortWithStatus(req, Response.Status.INTERNAL_SERVER_ERROR);
                e.printStackTrace();
                return;
            }

            UsuarioEntity usuarioEntity = usuarioService.getUserByNomeSenha(nomeSenha.nextToken(), nomeSenha.nextToken());
            if (usuarioEntity == null) {
                this.abortWithStatus(req, Response.Status.UNAUTHORIZED);
            } else {
                SessionApplication.setUsuario(usuarioEntity);
            }
        }
    }

    private StringTokenizer extractUsernamePasswordFromBasic(String basic) throws IOException {
        final String encodedUserPassword = basic.replaceFirst(AUTHENTICATION_SCHEME + " ", "");

        String usernameAndPassword;
        usernameAndPassword = new String(Base64.decode(encodedUserPassword));

        return new StringTokenizer(usernameAndPassword, ":");
    }

    private void abortWithStatus(ContainerRequestContext requestContext, Response.Status status) {
        requestContext.abortWith(Response.status(status).header(HttpHeaders.WWW_AUTHENTICATE, AUTHENTICATION_SCHEME).build());
    }

}
