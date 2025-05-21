package com.eduardo.biblioteca.controllers;

import com.eduardo.biblioteca.dtos.EmprestimoDTO;
import com.eduardo.biblioteca.services.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping
    public ResponseEntity<Page<EmprestimoDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(emprestimoService.findAll(pageable));
    }

    @GetMapping("/{usuarioId}/{livroId}")
    public ResponseEntity<EmprestimoDTO> findById(@PathVariable Long usuarioId, @PathVariable Long livroId) {
        return ResponseEntity.ok().body(emprestimoService.findById(usuarioId, livroId));
    }

    @PostMapping("/{usuarioId}/{livroId}")
    public ResponseEntity<EmprestimoDTO> realizarEmprestimo(@PathVariable Long usuarioId, @PathVariable Long livroId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoService.realizarEmprestimo(usuarioId, livroId));
    }

}
