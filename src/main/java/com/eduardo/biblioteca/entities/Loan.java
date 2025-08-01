package com.eduardo.biblioteca.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_loan")
public class Loan {

    @EmbeddedId
    private LoanPK id = new LoanPK();

    @Getter @Setter
    private LocalDate loanDate;

    @Getter @Setter
    private LocalDate returnDate;

    public Loan() {
    }

    public Loan(User user, Book book, LocalDate loanDate, LocalDate returnDate) {
        id.setUser(user);
        id.setBook(book);
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public void renew() {
        this.returnDate = this.getReturnDate().plusDays(7);
    }

    public User getUser() {
        return id.getUser();
    }

    public void setUser(User user) {
        id.setUser(user);
    }

    public Book getBook() {
        return id.getBook();
    }

    public void setBook(Book book) {
        id.setBook(book);
    }

}