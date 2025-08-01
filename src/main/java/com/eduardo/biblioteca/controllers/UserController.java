package com.eduardo.biblioteca.controllers;

import com.eduardo.biblioteca.dtos.UserDTO;
import com.eduardo.biblioteca.projections.UserLoanProjection;
import com.eduardo.biblioteca.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(userService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/{id}/loans")
    public ResponseEntity<Page<UserLoanProjection>> findUserLoans(@PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(userService.findUserLoans(id, pageable));
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserDTO dto) {
        dto = userService.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserDTO dto) {
        dto = userService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/deposit/{amount}")
    public ResponseEntity<Void> deposit(@PathVariable Long id, @PathVariable BigDecimal amount) {
        userService.deposit(id, amount);
        return ResponseEntity.ok().build();
    }

}
