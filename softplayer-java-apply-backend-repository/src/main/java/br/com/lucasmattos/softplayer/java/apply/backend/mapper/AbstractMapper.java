package br.com.lucasmattos.softplayer.java.apply.backend.mapper;

import br.com.lucasmattos.softplayer.java.apply.backend.dto.AbstractEntityDTO;
import br.com.lucasmattos.softplayer.java.apply.backend.entity.AbstractEntity;

import java.util.ArrayList;
import java.util.List;

public interface AbstractMapper<O extends AbstractEntity, D extends AbstractEntityDTO> {

    D toDTO(O object);
    O toObject(D dto);

    default List<D> toDTOs(List<O> listObject){
        List<D> retornoDTOs = new ArrayList<>();
        for (O obj : listObject){
            retornoDTOs.add(toDTO(obj));
        }
        return retornoDTOs;
    }

    default List<O> toObjects(List<D> listDTO) {
        List<O> retornoObjs = new ArrayList<>();
        for (D dto : listDTO){
            retornoObjs.add(toObject(dto));
        }
        return retornoObjs;
    }
}
