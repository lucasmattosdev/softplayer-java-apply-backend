package br.com.lucasmattos.softplayer.java.apply.backend.repository;

import br.com.lucasmattos.softplayer.java.apply.backend.dto.UsuarioDTO;
import br.com.lucasmattos.softplayer.java.apply.backend.entity.UsuarioEntity;
import br.com.lucasmattos.softplayer.java.apply.backend.exception.RepositoryException;
import br.com.lucasmattos.softplayer.java.apply.backend.mapper.UsuarioMapper;
import br.com.lucasmattos.softplayer.java.apply.backend.service.UsuarioService;

import javax.inject.Named;
import javax.ws.rs.core.Response;
import java.io.Serializable;

@Named
public class UsuarioRepository extends AbstractRepository<UsuarioMapper, UsuarioService> implements Serializable {

    public void validarUsuarioSenha(UsuarioDTO dto) throws RepositoryException {
        if (dto.getNome() == null || dto.getNome().isEmpty() || dto.getSenha() == null || dto.getSenha().isEmpty()) {
            throw new RepositoryException(Response.Status.NOT_ACCEPTABLE, "Você deve preencher todos os campos");
        } else {
            UsuarioEntity usuario = service.getUserByNomeSenha(dto.getNome(), dto.getSenha());
            if (usuario == null) {
                throw new RepositoryException(Response.Status.NOT_ACCEPTABLE, "Usuário ou senha inválidos");
            }
        }
    }

}
