package com.eduardo.biblioteca.dtos;

import com.eduardo.biblioteca.entities.Book;
import lombok.Data;

@Data
public class BookMinDTO {

    private Long id;
    private String name;

    public BookMinDTO(Book entity) {
        this.id = entity.getId();
        this.name = entity.getTitle();
    }

}
