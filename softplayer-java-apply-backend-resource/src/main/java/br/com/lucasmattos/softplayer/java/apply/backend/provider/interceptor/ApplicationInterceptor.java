package br.com.lucasmattos.softplayer.java.apply.backend.provider.interceptor;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.Collections;
import java.util.List;

@Provider
public class ApplicationInterceptor implements ContainerRequestFilter {

    private static final List<String> clientsAccepted = Collections.singletonList("5D6CC0D0-F1ED-40FD-AB8E-C932C236088A");

    @Override
    public void filter(ContainerRequestContext req) {
        try {
            validarHeaders(req.getHeaders());
        } catch (Exception ex) {
            req.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(ex.getMessage()).build());
        }
    }

    private void validarHeaders(MultivaluedMap<String, String> headers) throws Exception {
        if (headers.getFirst("Client-Id") == null || !clientsAccepted.contains(headers.getFirst("Client-Id"))){
            throw new Exception("Requisição Inválida!");
        }
    }
}
