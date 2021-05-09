package br.com.lucasmattos.softplayer.java.apply.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioDTO extends AbstractEntityDTO {
    private String nome;
    private String senha;
}
