package com.eduardo.biblioteca.repositories;

import com.eduardo.biblioteca.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
