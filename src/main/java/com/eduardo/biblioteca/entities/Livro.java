package com.eduardo.biblioteca.entities;

import com.eduardo.biblioteca.services.exceptions.LivroNaoDisponivelException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "tb_livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String autor;
    private boolean disponivel;

    @ManyToMany
    @JoinTable(name = "tb_livro_genero",
    joinColumns = @JoinColumn(name = "livro_id"),
    inverseJoinColumns = @JoinColumn(name = "genero_id"))
    private Set<Genero> generos = new HashSet<>();

    @Getter
    @OneToMany(mappedBy = "id.livro")
    private Set<Emprestimo> emprestimos = new HashSet<>();

    public List<Usuario> getUsuarios() {
        return emprestimos.stream().map(x -> x.getUsuario()).toList();
    }

    public boolean verifDisponibilidade() {
        return disponivel;
    }

    public void emprestar() {
        if (!disponivel) {
            throw new LivroNaoDisponivelException("Este livro não está disponível.");
        }
        this.disponivel = false;
    }

}
