package br.com.lucasmattos.softplayer.java.apply.backend.mapper;

import br.com.lucasmattos.softplayer.java.apply.backend.dto.UsuarioDTO;
import br.com.lucasmattos.softplayer.java.apply.backend.entity.UsuarioEntity;
import br.com.lucasmattos.softplayer.java.apply.backend.service.UsuarioService;

import javax.inject.Inject;

public class UsuarioMapper implements AbstractMapper<UsuarioEntity, UsuarioDTO> {

    @Inject
    UsuarioService usuarioService;

    @Override
    public UsuarioDTO toDTO(UsuarioEntity obj) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(obj.getId());
        dto.setNome(obj.getNome());
        return dto;
    }

    @Override
    public UsuarioEntity toObject(UsuarioDTO dto) {
        UsuarioEntity obj = new UsuarioEntity();
        if (dto.getId() != null){
            obj = usuarioService.getById(dto.getId());
        }

        if (dto.getNome() != null){
            obj.setNome(obj.getNome());
        }
        if (dto.getSenha() != null){
            obj.setSenha(obj.getSenha());
        }
        return obj;
    }
}
