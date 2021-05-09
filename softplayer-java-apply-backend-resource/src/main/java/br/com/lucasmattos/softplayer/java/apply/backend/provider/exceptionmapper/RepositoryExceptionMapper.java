package br.com.lucasmattos.softplayer.java.apply.backend.provider.exceptionmapper;

import br.com.lucasmattos.softplayer.java.apply.backend.exception.RepositoryException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;

@Provider
public class RepositoryExceptionMapper implements ExceptionMapper<RepositoryException> {

    @Override
    public Response toResponse(RepositoryException ex) {
        Response.ResponseBuilder response = Response.status(ex.getResponseStatus());
        if (ex.getMessage() != null && !ex.getMessage().isEmpty()){
            HashMap<String, String> jsonMap = new HashMap<>();
            jsonMap.put("error", ex.getMessage());
            response = response.type(MediaType.APPLICATION_JSON).entity(jsonMap);
        }
        return response.build();
    }
}
