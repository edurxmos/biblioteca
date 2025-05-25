package com.eduardo.biblioteca.repositories;

import com.eduardo.biblioteca.dtos.EmprestimoDTO;
import com.eduardo.biblioteca.entities.Usuario;
import com.eduardo.biblioteca.projections.UsuarioEmprestimosProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(nativeQuery = true, value = "SELECT livro.nome, data_emprestimo, data_devolucao FROM TB_EMPRESTIMO " +
            "JOIN tb_livro livro ON livro_id = livro.id\n" +
            "WHERE usuario_id = :id")
    public Page<UsuarioEmprestimosProjection> usuarioEmprestimos(@Param("id") Long id, Pageable pageable);

}
