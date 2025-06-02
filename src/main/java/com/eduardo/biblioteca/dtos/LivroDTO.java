package com.eduardo.biblioteca.dtos;

import com.eduardo.biblioteca.entities.Genero;
import com.eduardo.biblioteca.entities.Livro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class LivroDTO {

    private Long id;

    @NotBlank(message = "O livro precisa ter um nome.")
    @Size(min = 3, max = 80, message = "O nome do livro precisa ter de 3 a 80 caracteres.")
    private String nome;

    @NotBlank(message = "O livro precisa de um autor.")
    @Size(min = 3, max = 80, message = "O nome do autor precisa ter de 3 a 80 caracteres.")
    private String autor;

    private BigDecimal preco;

    private boolean disponivel;

    private Set<String> generos = new HashSet<>();

    public LivroDTO() {
    }

    public LivroDTO(Livro entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.autor = entity.getAutor();
        this.preco = entity.getPreco();
        this.disponivel = entity.isDisponivel();

        for (Genero x : entity.getGeneros()) {
            generos.add(x.getNome());
        }

    }
}
