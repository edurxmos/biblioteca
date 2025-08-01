package com.eduardo.biblioteca.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "tb_genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Book> books = new HashSet<>();

}