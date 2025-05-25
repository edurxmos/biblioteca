package com.eduardo.biblioteca.projections;

import java.time.LocalDate;

public interface EmprestimosDataProjection {

    String getNome();
    LocalDate getDataEmprestimo();
    LocalDate getDataDevolucao();

}
