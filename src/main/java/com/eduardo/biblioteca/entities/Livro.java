package com.eduardo.biblioteca.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "tb_livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String autor;
    private boolean disponivel;

    @ManyToMany(mappedBy = "livros")
    private Set<Emprestimo> emprestimos = new HashSet<>();

}
