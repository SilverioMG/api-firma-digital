package com.prueba.firmadigital.domain;

public class DomainValidationException extends RuntimeException{

    public DomainValidationException(String message) {
        super(message);
    }
}
