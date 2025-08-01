package com.eduardo.biblioteca.services;

import com.eduardo.biblioteca.dtos.LoanDTO;
import com.eduardo.biblioteca.dtos.LoanMinDTO;
import com.eduardo.biblioteca.entities.Loan;
import com.eduardo.biblioteca.entities.LoanPK;
import com.eduardo.biblioteca.entities.Book;
import com.eduardo.biblioteca.entities.User;
import com.eduardo.biblioteca.projections.LoanDateProjection;
import com.eduardo.biblioteca.repositories.LoanRepository;
import com.eduardo.biblioteca.repositories.BookRepository;
import com.eduardo.biblioteca.repositories.UserRepository;
import com.eduardo.biblioteca.services.exceptions.BookNotAvailableException;
import com.eduardo.biblioteca.services.exceptions.NotFoundException;
import com.eduardo.biblioteca.services.exceptions.InsufficientBalanceException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Transactional(readOnly = true)
    public Page<LoanMinDTO> findAll(Pageable pageable) {
        return loanRepository.findAll(pageable).map(LoanMinDTO::new);
    }

    @Transactional(readOnly = true)
    public LoanMinDTO findById(Long userId, Long bookId) {
        User user = userRepository.getReferenceById(userId);
        Book book = bookRepository.getReferenceById(bookId);

        LoanPK id = new LoanPK();
        id.setUser(user);
        id.setBook(book);

        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Loan not found"));

        return new LoanMinDTO(loan);
    }

    @Transactional
    public LoanDTO createLoan(Long userId, Long bookId) {
        try {
            User user = userRepository.getReferenceById(userId);
            Book book = bookRepository.getReferenceById(bookId);

            BigDecimal balance = user.getBalance();
            BigDecimal price = book.getPrice();

            if (!book.isAvailable()) {
                throw new BookNotAvailableException("This book is not available.");
            }
            if (balance.compareTo(price) < 0) {
                throw new InsufficientBalanceException("Your balance is insufficient");
            }

            book.loanOut();
            user.setBalance(balance.subtract(price));
            Loan entity = new Loan(user, book, LocalDate.now(), LocalDate.now().plusDays(7));
            loanRepository.save(entity);
            return new LoanDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException("Resource not found");
        }
    }

    @Transactional
    public LoanDTO renewLoan(Long userId, Long bookId) {
        try {
            User user = userRepository.getReferenceById(userId);
            Book book = bookRepository.getReferenceById(bookId);

            BigDecimal balance = user.getBalance();
            BigDecimal renewalPrice = new BigDecimal("5");

            LoanPK id = new LoanPK();
            id.setUser(user);
            id.setBook(book);

            if (balance.compareTo(renewalPrice) < 0) {
                throw new InsufficientBalanceException("Your balance is insufficient");
            }

            Loan entity = loanRepository.getReferenceById(id);
            entity.renew();
            user.setBalance(balance.subtract(renewalPrice));
            loanRepository.save(entity);
            return new LoanDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException("Resource not found");
        }
    }

    @Transactional(readOnly = true)
    public Page<LoanDateProjection> findByDate(LocalDate date, Pageable pageable) {
        Page<LoanDateProjection> result = loanRepository.findByDate(date, pageable);
        if (result.isEmpty()) {
            throw new NotFoundException("No loans found on this date");
        }
        return result;
    }

}