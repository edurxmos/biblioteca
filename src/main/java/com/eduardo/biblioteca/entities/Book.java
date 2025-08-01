package com.eduardo.biblioteca.entities;

import com.eduardo.biblioteca.services.exceptions.BookNotAvailableException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "tb_book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private boolean available;
    private BigDecimal price;

    @ManyToMany
    @JoinTable(
            name = "tb_book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    @Getter
    @OneToMany(mappedBy = "id.book")
    private Set<Loan> loans = new HashSet<>();

    public List<User> getUsers() {
        return loans.stream().map(x -> x.getUser()).toList();
    }

    public boolean isAvailable() {
        return available;
    }

    public void loanOut() {
        if (!available) {
            throw new BookNotAvailableException("This book is not available.");
        }
        this.available = false;
    }

}