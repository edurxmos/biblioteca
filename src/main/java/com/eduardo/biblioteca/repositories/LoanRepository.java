package com.eduardo.biblioteca.repositories;

import com.eduardo.biblioteca.entities.Loan;
import com.eduardo.biblioteca.entities.LoanPK;
import com.eduardo.biblioteca.projections.LoanDateProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface LoanRepository extends JpaRepository<Loan, LoanPK> {

    @Query(nativeQuery = true, value = "SELECT book.title, loan_date, return_date FROM TB_LOAN " +
            "JOIN tb_book book ON book_id = book.id " +
            "WHERE loan_date = :date")
    Page<LoanDateProjection> findByDate(@Param("date") LocalDate date, Pageable pageable);

}