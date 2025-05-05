package com.eduardo.biblioteca.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String autor;
    private boolean disponivel;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
