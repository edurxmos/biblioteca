package com.eduardo.biblioteca.dtos;

import com.eduardo.biblioteca.entities.Livro;
import lombok.Data;

@Data
public class LivroMinDTO {

    private Long id;
    private String nome;

    public LivroMinDTO(Livro entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
    }
}
