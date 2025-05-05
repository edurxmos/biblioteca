package com.eduardo.biblioteca.dtos;

import com.eduardo.biblioteca.entities.Livro;

public class LivroDTO {

    private Long id;
    private String nome;
    private String autor;

    public LivroDTO(Livro entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.autor = entity.getAutor();
    }
}
