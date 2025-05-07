package com.eduardo.biblioteca.dtos;

import com.eduardo.biblioteca.entities.Livro;
import lombok.Data;

@Data
public class LivroDTO {

    private Long id;
    private String nome;
    private String autor;
    private boolean disponivel;

    public LivroDTO() {
    }

    public LivroDTO(Livro entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.autor = entity.getAutor();
        this.disponivel = entity.isDisponivel();
    }
}
