package com.eduardo.biblioteca.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    @Getter
    @OneToMany(mappedBy = "id.user")
    private Set<Loan> loans = new HashSet<>();

    private BigDecimal balance;

    public List<Book> getBooks() {
        return loans.stream().map(x -> x.getBook()).toList();
    }

}
