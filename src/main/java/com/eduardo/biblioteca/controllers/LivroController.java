package com.eduardo.biblioteca.controllers;

import com.eduardo.biblioteca.dtos.LivroDTO;
import com.eduardo.biblioteca.services.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public ResponseEntity<Page<LivroDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(livroService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LivroDTO> insert(@Valid @RequestBody LivroDTO dto) {
        dto = livroService.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> update(@PathVariable Long id, @Valid @RequestBody LivroDTO dto) {
        dto = livroService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
