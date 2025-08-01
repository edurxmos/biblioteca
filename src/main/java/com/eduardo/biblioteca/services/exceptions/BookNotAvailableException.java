package com.eduardo.biblioteca.services.exceptions;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException(String message) {
        super(message);
    }
}