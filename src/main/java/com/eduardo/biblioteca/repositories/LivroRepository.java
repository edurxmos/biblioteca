package com.eduardo.biblioteca.repositories;

import com.eduardo.biblioteca.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
