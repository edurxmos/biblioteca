package com.eduardo.biblioteca.services;

import com.eduardo.biblioteca.dtos.BookDTO;
import com.eduardo.biblioteca.entities.Book;
import com.eduardo.biblioteca.repositories.BookRepository;
import com.eduardo.biblioteca.services.exceptions.DataBaseException;
import com.eduardo.biblioteca.services.exceptions.NotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional(readOnly = true)
    public Page<BookDTO> findAll(Pageable pageable) {
        Page<Book> result = bookRepository.findAll(pageable);
        return result.map(BookDTO::new);
    }

    @Transactional(readOnly = true)
    public BookDTO findById(Long id) {
        Book entity = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Resource not found"));
        return new BookDTO(entity);
    }

    @Transactional
    public BookDTO insert(BookDTO dto) {
        Book entity = new Book();
        copyDtoToEntity(dto, entity);
        entity.setAvailable(true);
        entity = bookRepository.save(entity);
        return new BookDTO(entity);
    }

    @Transactional
    public BookDTO update(Long id, BookDTO dto) {
        try {
            Book entity = bookRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            bookRepository.save(entity);
            return new BookDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException("Resource not found");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        try {
            if (!bookRepository.existsById(id)) {
                throw new NotFoundException("Resource not found");
            }
            bookRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Relational integrity violation");
        }
    }

    private void copyDtoToEntity(BookDTO dto, Book entity) {
        entity.setTitle(dto.getTitle());
        entity.setAuthor(dto.getAuthor());
    }

}