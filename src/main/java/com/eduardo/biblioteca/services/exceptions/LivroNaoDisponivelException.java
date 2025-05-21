package com.eduardo.biblioteca.services.exceptions;

public class LivroNaoDisponivelException extends RuntimeException {
    public LivroNaoDisponivelException(String msg) {
        super(msg);
    }
}
