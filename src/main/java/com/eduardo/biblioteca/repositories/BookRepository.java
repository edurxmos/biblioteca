package com.eduardo.biblioteca.repositories;

import com.eduardo.biblioteca.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
