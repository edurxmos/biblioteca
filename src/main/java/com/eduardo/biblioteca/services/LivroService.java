package com.eduardo.biblioteca.services;

import com.eduardo.biblioteca.dtos.LivroDTO;
import com.eduardo.biblioteca.entities.Livro;
import com.eduardo.biblioteca.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Transactional(readOnly = true)
    public List<LivroDTO> findAll() {
        List<Livro> list = livroRepository.findAll();
        return list.stream().map(x -> new LivroDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public LivroDTO findById(Long id) {
        Livro entity = livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado"));
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
        Livro entity = livroRepository.getReferenceById(id);
        copyDtoToEntity(dto, entity);
        livroRepository.save(entity);
        return new LivroDTO(entity);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        livroRepository.deleteById(id);
    }

    public void copyDtoToEntity(LivroDTO dto, Livro entity) {
        entity.setNome(dto.getNome());
        entity.setAutor(dto.getAutor());
    }
}
