package br.com.lucasmattos.softplayer.java.apply.backend.resource;

import br.com.lucasmattos.softplayer.java.apply.backend.dto.PaginationDTO;
import br.com.lucasmattos.softplayer.java.apply.backend.dto.PessoaDTO;
import br.com.lucasmattos.softplayer.java.apply.backend.exception.RepositoryException;
import br.com.lucasmattos.softplayer.java.apply.backend.repository.PessoaRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("pessoa")
public class PessoaResource extends AbstractResource<PessoaRepository> {

    @GET
    @Path("/{idPessoa}")
    public Response getPessoa(@PathParam("idPessoa") Long idPessoa) {
        return Response.ok(repository.getById(idPessoa)).build();
    }

    @PUT
    public Response criarPessoa(PessoaDTO pessoaDTO) throws RepositoryException {
        repository.criarPessoa(pessoaDTO);
        return Response.ok().build();
    }

    @POST
    public Response alterarPessoa(PessoaDTO pessoaDTO) throws RepositoryException {
        repository.alterarPessoa(pessoaDTO);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{idPessoa}")
    public Response deletarPessoa(@PathParam("idPessoa") Long idPessoa) {
        repository.deletarPessoa(idPessoa);
        return Response.ok().build();
    }

    @POST
    @Path("/consulta")
    public Response consultarPessoas(PaginationDTO paginationDTO) {
        return Response.ok(repository.consultarPessoas(paginationDTO)).build();
    }
}
