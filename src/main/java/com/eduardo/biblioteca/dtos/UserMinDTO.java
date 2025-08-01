package com.eduardo.biblioteca.dtos;

import com.eduardo.biblioteca.entities.User;
import lombok.Data;

@Data
public class UserMinDTO {

    private Long id;
    private String name;

    public UserMinDTO(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }

}
