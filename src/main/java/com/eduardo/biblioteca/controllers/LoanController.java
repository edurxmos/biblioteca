package com.eduardo.biblioteca.controllers;

import com.eduardo.biblioteca.dtos.LoanDTO;
import com.eduardo.biblioteca.dtos.LoanMinDTO;
import com.eduardo.biblioteca.projections.LoanDateProjection;
import com.eduardo.biblioteca.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public ResponseEntity<Page<LoanMinDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(loanService.findAll(pageable));
    }

    @GetMapping("/{userId}/{bookId}")
    public ResponseEntity<LoanMinDTO> findById(@PathVariable Long userId, @PathVariable Long bookId) {
        return ResponseEntity.ok(loanService.findById(userId, bookId));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<Page<LoanDateProjection>> findByDate(@PathVariable LocalDate date, Pageable pageable) {
        return ResponseEntity.ok(loanService.findByDate(date, pageable));
    }

    @PostMapping("/{userId}/{bookId}")
    public ResponseEntity<LoanDTO> createLoan(@PathVariable Long userId, @PathVariable Long bookId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.createLoan(userId, bookId));
    }

    @PutMapping("/{userId}/{bookId}")
    public ResponseEntity<LoanDTO> renewLoan(@PathVariable Long userId, @PathVariable Long bookId) {
        return ResponseEntity.ok(loanService.renewLoan(userId, bookId));
    }

}