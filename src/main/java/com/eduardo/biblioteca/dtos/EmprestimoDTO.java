package com.eduardo.biblioteca.dtos;

import com.eduardo.biblioteca.entities.Emprestimo;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmprestimoDTO {

    private UsuarioDTO usuario;
    private LivroDTO livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public EmprestimoDTO(Emprestimo entity) {
        this.usuario = new UsuarioDTO(entity.getUsuario());
        this.livro = new LivroDTO(entity.getLivro());
        this.dataEmprestimo = entity.getDataEmprestimo();
        this.dataDevolucao = entity.getDataDevolucao();
    }

}
