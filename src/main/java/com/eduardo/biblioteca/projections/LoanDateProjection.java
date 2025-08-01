package com.eduardo.biblioteca.projections;

import java.time.LocalDate;

public interface LoanDateProjection {

    String getTitle();
    LocalDate getLoanDate();
    LocalDate getReturnDate();

}