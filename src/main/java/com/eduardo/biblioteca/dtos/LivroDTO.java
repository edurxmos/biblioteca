package com.eduardo.biblioteca.dtos;

import com.eduardo.biblioteca.entities.Livro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LivroDTO {

    private Long id;

    @NotBlank(message = "O livro precisa ter um nome.")
    @Size(min = 3, max = 80, message = "O nome do livro precisa ter de 3 a 80 caracteres.")
    private String nome;

    @NotBlank(message = "O livro precisa de um autor.")
    @Size(min = 3, max = 80, message = "O nome do autor precisa ter de 3 a 80 caracteres.")
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
