package com.eduardo.biblioteca.dtos;

import com.eduardo.biblioteca.entities.Book;
import com.eduardo.biblioteca.entities.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class BookDTO {

    private Long id;

    @NotBlank(message = "Book must have a name.")
    @Size(min = 3, max = 80, message = "Book name must be between 3 and 80 characters.")
    private String title;

    @NotBlank(message = "Book must have an author.")
    @Size(min = 3, max = 80, message = "Author name must be between 3 and 80 characters.")
    private String author;

    private BigDecimal price;

    private boolean available;

    private Set<String> genres = new HashSet<>();

    public BookDTO() {
    }

    public BookDTO(Book entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.price = entity.getPrice();
        this.available = entity.isAvailable();

        for (Genre genre : entity.getGenres()) {
            genres.add(genre.getName());
        }
    }

}
