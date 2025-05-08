package com.eduardo.biblioteca.services.exceptions;

public class NaoEncontradoException extends RuntimeException {
    public NaoEncontradoException(String msg) {
        super(msg);
    }
}
