package com.eduardo.biblioteca.controllers;

import com.eduardo.biblioteca.dtos.UsuarioDTO;
import com.eduardo.biblioteca.projections.UsuarioEmprestimosProjection;
import com.eduardo.biblioteca.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(usuarioService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @GetMapping("/{id}/emprestimos")
    public ResponseEntity<Page<UsuarioEmprestimosProjection>> usuarioEmprestimos(@PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(usuarioService.usuarioEmprestimos(id, pageable));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> insert(@Valid @RequestBody UsuarioDTO dto) {
        dto = usuarioService.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @Valid @RequestBody UsuarioDTO dto) {
        dto = usuarioService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}/deposito/{valor}")
    public ResponseEntity<Void> deposito(@PathVariable Long id, @PathVariable BigDecimal valor) {
        usuarioService.deposito(id, valor);
        return ResponseEntity.ok().build();
    }
}
