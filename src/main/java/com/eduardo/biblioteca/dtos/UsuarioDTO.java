package com.eduardo.biblioteca.dtos;

import com.eduardo.biblioteca.entities.Usuario;
import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;
    private String nome;

    public UsuarioDTO(Usuario entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
    }
}
