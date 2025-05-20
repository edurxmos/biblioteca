package com.eduardo.biblioteca.repositories;

import com.eduardo.biblioteca.entities.Emprestimo;
import com.eduardo.biblioteca.entities.EmprestimoPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, EmprestimoPK> {
}
