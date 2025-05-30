package com.eduardo.biblioteca.services;

import com.eduardo.biblioteca.dtos.UsuarioDTO;
import com.eduardo.biblioteca.entities.Usuario;
import com.eduardo.biblioteca.projections.UsuarioEmprestimosProjection;
import com.eduardo.biblioteca.repositories.LivroRepository;
import com.eduardo.biblioteca.repositories.UsuarioRepository;
import com.eduardo.biblioteca.services.exceptions.DataBaseException;
import com.eduardo.biblioteca.services.exceptions.DepositoException;
import com.eduardo.biblioteca.services.exceptions.NaoEncontradoException;
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
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LivroRepository livroRepository;

    @Transactional(readOnly = true)
    public Page<UsuarioDTO> findAll(Pageable pageable) {
        Page<Usuario> result = usuarioRepository.findAll(pageable);
        return result.map(x -> new UsuarioDTO(x));
    }

    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id) {
        Usuario entity = usuarioRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Recurso não encontrado"));
        return new UsuarioDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<UsuarioEmprestimosProjection> usuarioEmprestimos(Long id, Pageable pageable) {
        if (!usuarioRepository.existsById(id)) {
            throw new NaoEncontradoException("Recurso não encontrado");
        } else {
            return usuarioRepository.usuarioEmprestimos(id, pageable);
        }
    }

    @Transactional
    public UsuarioDTO insert(UsuarioDTO dto) {
        Usuario entity = new Usuario();
        copyDtoToEntity(dto, entity);
        entity = usuarioRepository.save(entity);
        return new UsuarioDTO(entity);
    }

    @Transactional
    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        try {
            Usuario entity = usuarioRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = usuarioRepository.save(entity);
            return new UsuarioDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new NaoEncontradoException("Recurso não encontrado");
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        try {
            if (!usuarioRepository.existsById(id)) {
                throw new NaoEncontradoException("Recurso não encontrado");
            }
            usuarioRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Falha de integridade relacional");
        }
    }

    @Transactional
    public void deposito(Long id, BigDecimal valor) {
        try {
            if (valor.compareTo(new BigDecimal("1.00")) == -1) {
                throw new DepositoException("Valor do depósito deve ser igual ou maior que 1 real");
            }
            Usuario entity = usuarioRepository.getReferenceById(id);
            entity.setSaldo(entity.getSaldo().add(valor));
            usuarioRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new NaoEncontradoException("Recurso não encontrado");
        }
    }

    public void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
    }
}
