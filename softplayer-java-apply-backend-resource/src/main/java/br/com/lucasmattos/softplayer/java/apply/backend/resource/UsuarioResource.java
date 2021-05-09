package br.com.lucasmattos.softplayer.java.apply.backend.resource;

import br.com.lucasmattos.softplayer.java.apply.backend.repository.UsuarioRepository;
import br.com.lucasmattos.softplayer.java.apply.backend.dto.UsuarioDTO;
import br.com.lucasmattos.softplayer.java.apply.backend.exception.RepositoryException;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("usuario")
public class UsuarioResource extends AbstractResource<UsuarioRepository> {

    @POST
    @PermitAll
    @Path("/validarNomeSenha")
    public Response login(UsuarioDTO usuario) throws RepositoryException {
        repository.validarUsuarioSenha(usuario);
        return Response.ok().build();
    }

}
