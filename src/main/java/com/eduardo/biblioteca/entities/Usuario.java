package com.eduardo.biblioteca.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Column(unique = true)
    private String email;

    @Getter
    @OneToMany(mappedBy = "id.usuario")
    private Set<Emprestimo> emprestimos = new HashSet<>();

    public List<Livro> getLivros() {
        return emprestimos.stream().map(x -> x.getLivro()).toList();
    }



}
