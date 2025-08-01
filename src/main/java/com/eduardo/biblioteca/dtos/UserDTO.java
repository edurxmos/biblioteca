package com.eduardo.biblioteca.dtos;

import com.eduardo.biblioteca.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserDTO {

    private Long id;

    @NotBlank(message = "User must have a name.")
    @Size(min = 3, max = 80, message = "Name must be between 3 and 80 characters.")
    private String name;

    @NotBlank(message = "User must have an email.")
    @Size(min = 3, max = 80)
    private String email;

    private BigDecimal balance = BigDecimal.ZERO;

    public UserDTO() {
    }

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.balance = entity.getBalance();
    }

}
