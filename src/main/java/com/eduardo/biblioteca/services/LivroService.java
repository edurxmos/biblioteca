package com.eduardo.biblioteca.services;

import com.eduardo.biblioteca.dtos.LivroDTO;
import com.eduardo.biblioteca.entities.Livro;
import com.eduardo.biblioteca.repositories.LivroRepository;
import com.eduardo.biblioteca.services.exceptions.NaoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Transactional(readOnly = true)
    public Page<LivroDTO> findAll(Pageable pageable) {
        Page<Livro> result = livroRepository.findAll(pageable);
        return result.map(x -> new LivroDTO(x));
    }

    @Transactional(readOnly = true)
    public LivroDTO findById(Long id) {
        Livro entity = livroRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Recurso não encontrado"));
        return new LivroDTO(entity);
    }

    @Transactional
    public LivroDTO insert(LivroDTO dto) {
        Livro entity = new Livro();
        copyDtoToEntity(dto, entity);
        entity.setDisponivel(true);
        entity = livroRepository.save(entity);
        return new LivroDTO(entity);
    }

    @Transactional
    public LivroDTO update(Long id, LivroDTO dto) {
        try {
            Livro entity = livroRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            livroRepository.save(entity);
            return new LivroDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new NaoEncontradoException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new NaoEncontradoException("Recurso não encontrado");
        }

        livroRepository.deleteById(id);
    }

    public void copyDtoToEntity(LivroDTO dto, Livro entity) {
        entity.setNome(dto.getNome());
        entity.setAutor(dto.getAutor());
    }
}
