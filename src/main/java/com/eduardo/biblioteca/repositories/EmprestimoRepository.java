package com.eduardo.biblioteca.repositories;

import com.eduardo.biblioteca.entities.Emprestimo;
import com.eduardo.biblioteca.entities.EmprestimoPK;
import com.eduardo.biblioteca.projections.EmprestimosDataProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, EmprestimoPK> {

    @Query(nativeQuery = true, value = "SELECT livro.nome, data_emprestimo, data_devolucao FROM TB_EMPRESTIMO " +
            "JOIN tb_livro livro ON livro_id = livro.id " +
            "WHERE data_emprestimo = :data")
    public Page<EmprestimosDataProjection> findByData(@Param("data") LocalDate data, Pageable pageable);

}
