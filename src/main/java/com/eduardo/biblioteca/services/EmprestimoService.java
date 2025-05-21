package com.eduardo.biblioteca.services;

import com.eduardo.biblioteca.dtos.EmprestimoDTO;
import com.eduardo.biblioteca.entities.Emprestimo;
import com.eduardo.biblioteca.entities.EmprestimoPK;
import com.eduardo.biblioteca.entities.Livro;
import com.eduardo.biblioteca.entities.Usuario;
import com.eduardo.biblioteca.repositories.EmprestimoRepository;
import com.eduardo.biblioteca.repositories.LivroRepository;
import com.eduardo.biblioteca.repositories.UsuarioRepository;
import com.eduardo.biblioteca.services.exceptions.LivroNaoDisponivelException;
import com.eduardo.biblioteca.services.exceptions.NaoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LivroRepository livroRepository;

    @Transactional(readOnly = true)
    public Page<EmprestimoDTO> findAll(Pageable pageable) {
        return emprestimoRepository.findAll(pageable).map(x -> new EmprestimoDTO(x));
    }

    @Transactional(readOnly = true)
    public EmprestimoDTO findById(Long usuarioId, Long livroId) {
        Usuario usuario = usuarioRepository.getReferenceById(usuarioId);

        Livro livro = livroRepository.getReferenceById(livroId);

        EmprestimoPK id = new EmprestimoPK();
        id.setUsuario(usuario);
        id.setLivro(livro);

        Emprestimo emprestimo = emprestimoRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Empréstimo não encontrado"));

        return new EmprestimoDTO(emprestimo);
    }

    @Transactional
    public EmprestimoDTO realizarEmprestimo(Long usuarioId, Long livroId) {
        try {
            Usuario usuario = usuarioRepository.getReferenceById(usuarioId);
            Livro livro = livroRepository.getReferenceById(livroId);

            if (!livro.verifDisponibilidade()) {
                throw new LivroNaoDisponivelException("Este livro não está disponível.");
            } else {
                livro.emprestar();
                Emprestimo entity = new Emprestimo(usuario, livro, LocalDate.now(), LocalDate.now().plusDays(7));
                emprestimoRepository.save(entity);
                return new EmprestimoDTO(entity);
            }
        } catch (EntityNotFoundException e) {
            throw new NaoEncontradoException("Recurso não encontrado");
        }
    }



}
