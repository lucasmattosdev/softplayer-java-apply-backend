package br.com.lucasmattos.softplayer.java.apply.backend.entity;

import br.com.lucasmattos.softplayer.java.apply.backend.enums.SexoEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "pessoa")
public class PessoaEntity extends AbstractEntity {
    @NotNull
    @Length(min = 2, max = 50, message = "O nome da pessoa deve ser entre 2 a 30 caracteres")
    private String nome;

    @Enumerated(EnumType.STRING)
    private SexoEnum sexo;

    @Email
    @Length(max = 80, message = "O email da pessoa deve conter no máximo 150 caracteres")
    private String email;

    @NotNull
    private Date dataNascimento;

    @Length(min = 3, max = 30, message = "O naturalidade da pessoa deve ser entre 3 a 30 caracteres")
    private String naturalidade;

    @Length(min = 3, max = 30, message = "O nacionalidade da pessoa deve ser entre 3 a 30 caracteres")
    private String nacionalidade;

    @NotNull
    @Length(min = 11, max = 11, message = "Seu CPF deve conter 11 dígitos")
    @Column(unique = true)
    private String cpf;

    @NotNull
    private Timestamp criadoEm;

    private Timestamp ultimaModificacao;
}