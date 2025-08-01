package com.eduardo.biblioteca.services;

import com.eduardo.biblioteca.dtos.UserDTO;
import com.eduardo.biblioteca.entities.User;
import com.eduardo.biblioteca.projections.UserLoanProjection;
import com.eduardo.biblioteca.repositories.BookRepository;
import com.eduardo.biblioteca.repositories.UserRepository;
import com.eduardo.biblioteca.services.exceptions.DataBaseException;
import com.eduardo.biblioteca.services.exceptions.DepositException;
import com.eduardo.biblioteca.services.exceptions.NotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> result = userRepository.findAll(pageable);
        return result.map(UserDTO::new);
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Resource not found"));
        return new UserDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<UserLoanProjection> findUserLoans(Long id, Pageable pageable) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("Resource not found");
        }
        return userRepository.findUserLoans(id, pageable);
    }

    @Transactional
    public UserDTO insert(UserDTO dto) {
        User entity = new User();
        copyDtoToEntity(dto, entity);
        entity.setBalance(BigDecimal.ZERO);
        entity = userRepository.save(entity);
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO update(Long id, UserDTO dto) {
        try {
            User entity = userRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = userRepository.save(entity);
            return new UserDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException("Resource not found");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        try {
            if (!userRepository.existsById(id)) {
                throw new NotFoundException("Resource not found");
            }
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Relational integrity violation");
        }
    }

    @Transactional
    public void deposit(Long id, BigDecimal amount) {
        try {
            if (amount.compareTo(new BigDecimal("1.00")) < 0) {
                throw new DepositException("Deposit amount must be at least 1.00");
            }
            User entity = userRepository.getReferenceById(id);
            entity.setBalance(entity.getBalance().add(amount));
            userRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException("Resource not found");
        }
    }

    private void copyDtoToEntity(UserDTO dto, User entity) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
    }

}