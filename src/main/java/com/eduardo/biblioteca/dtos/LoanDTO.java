package com.eduardo.biblioteca.dtos;

import com.eduardo.biblioteca.entities.Loan;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LoanDTO {

    private UserDTO user;
    private BookDTO book;
    private LocalDate loanDate;
    private LocalDate returnDate;

    public LoanDTO(Loan entity) {
        this.user = new UserDTO(entity.getUser());
        this.book = new BookDTO(entity.getBook());
        this.loanDate = entity.getLoanDate();
        this.returnDate = entity.getReturnDate();
    }

}