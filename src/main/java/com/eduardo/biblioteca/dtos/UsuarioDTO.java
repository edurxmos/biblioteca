package com.eduardo.biblioteca.dtos;

import com.eduardo.biblioteca.entities.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "O usuário precisa ter um nome.")
    @Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres.")
    private String nome;

    @NotBlank(message = "O usuário precisa ter um email.")
    @Size(min = 3, max = 80)
    private String email;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.email = entity.getEmail();
    }
}
