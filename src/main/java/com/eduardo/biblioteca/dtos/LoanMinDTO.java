package com.eduardo.biblioteca.dtos;

import com.eduardo.biblioteca.entities.Loan;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LoanMinDTO {

    private UserMinDTO user;
    private BookMinDTO book;
    private LocalDate loanDate;
    private LocalDate returnDate;

    public LoanMinDTO(Loan entity) {
        this.user = new UserMinDTO(entity.getUser());
        this.book = new BookMinDTO(entity.getBook());
        this.loanDate = entity.getLoanDate();
        this.returnDate = entity.getReturnDate();
    }

}