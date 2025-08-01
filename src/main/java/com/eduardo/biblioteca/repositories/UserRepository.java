package com.eduardo.biblioteca.repositories;

import com.eduardo.biblioteca.entities.User;
import com.eduardo.biblioteca.projections.UserLoanProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "SELECT book.name, loan_date, return_date FROM TB_LOAN " +
            "JOIN tb_book book ON book_id = book.id " +
            "WHERE user_id = :id")
    Page<UserLoanProjection> findUserLoans(@Param("id") Long id, Pageable pageable);

}