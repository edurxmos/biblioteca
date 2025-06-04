package com.eduardo.biblioteca.dtos;

import com.eduardo.biblioteca.entities.Emprestimo;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmprestimoMinDTO {

    private UsuarioMinDTO usuario;
    private LivroMinDTO livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public EmprestimoMinDTO(Emprestimo entity) {
        this.usuario = new UsuarioMinDTO(entity.getUsuario());
        this.livro = new LivroMinDTO(entity.getLivro());
        this.dataEmprestimo = entity.getDataEmprestimo();
        this.dataDevolucao = entity.getDataDevolucao();
    }
}
