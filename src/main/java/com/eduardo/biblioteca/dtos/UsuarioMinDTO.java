package com.eduardo.biblioteca.dtos;

import com.eduardo.biblioteca.entities.Usuario;
import lombok.Data;

@Data
public class UsuarioMinDTO {

    private Long id;
    private String nome;

    public UsuarioMinDTO(Usuario entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
    }
}
