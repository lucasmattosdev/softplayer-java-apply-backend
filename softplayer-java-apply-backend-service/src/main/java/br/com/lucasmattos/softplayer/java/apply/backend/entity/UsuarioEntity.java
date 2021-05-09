package br.com.lucasmattos.softplayer.java.apply.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class UsuarioEntity extends AbstractEntity {
    @NotNull
    private String nome;

    @NotNull
    private String senha;
}