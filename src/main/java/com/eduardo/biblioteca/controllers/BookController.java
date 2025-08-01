package com.eduardo.biblioteca.controllers;

import com.eduardo.biblioteca.dtos.BookDTO;
import com.eduardo.biblioteca.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<Page<BookDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(bookService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @PostMapping
    public ResponseEntity<BookDTO> insert(@Valid @RequestBody BookDTO dto) {
        dto = bookService.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable Long id, @Valid @RequestBody BookDTO dto) {
        dto = bookService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
