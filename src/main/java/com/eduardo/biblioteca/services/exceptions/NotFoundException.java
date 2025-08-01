package com.eduardo.biblioteca.services.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) {
        super(msg);
    }
}
