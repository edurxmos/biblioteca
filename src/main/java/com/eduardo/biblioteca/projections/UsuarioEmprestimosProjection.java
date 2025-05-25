package com.eduardo.biblioteca.projections;

import java.time.LocalDate;

public interface UsuarioEmprestimosProjection {

    String getNome();
    LocalDate getDataEmprestimo();
    LocalDate getDataDevolucao();

}
