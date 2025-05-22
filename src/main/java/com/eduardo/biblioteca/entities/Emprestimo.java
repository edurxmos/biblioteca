package com.eduardo.biblioteca.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_emprestimo")
public class Emprestimo {

    @EmbeddedId
    private EmprestimoPK id = new EmprestimoPK();

    @Getter @Setter
    private LocalDate dataEmprestimo;
    @Getter @Setter
    private LocalDate dataDevolucao;

    public Emprestimo() {
    }

    public Emprestimo(Usuario usuario, Livro livro, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        id.setUsuario(usuario);
        id.setLivro(livro);
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public void renovar() {
        this.dataDevolucao = this.getDataDevolucao().plusDays(7);
    }

    public Usuario getUsuario() {
        return id.getUsuario();
    }

    public void setUsuario(Usuario usuario) {
        id.setUsuario(usuario);
    }

    public Livro getLivro() {
        return id.getLivro();
    }

    public void setLivro(Livro livro) {
        id.setLivro(livro);
    }

}
