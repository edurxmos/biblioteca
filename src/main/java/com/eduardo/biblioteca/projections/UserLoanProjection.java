package com.eduardo.biblioteca.projections;

import java.time.LocalDate;

public interface UserLoanProjection {

    String getName();
    LocalDate getLoanDate();
    LocalDate getReturnDate();

}