package br.com.lucasmattos.softplayer.java.apply.backend.dto;

import br.com.lucasmattos.softplayer.java.apply.backend.enums.SexoEnum;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Setter
@Getter
public class PessoaDTO extends AbstractEntityDTO {
    private String nome;
    private SexoEnum sexo;
    private String email;
    private Date dataNascimento;
    private String naturalidade;
    private String nacionalidade;
    private String cpf;
    private Timestamp criadoEm;
    private Timestamp ultimaModificacao;
}
