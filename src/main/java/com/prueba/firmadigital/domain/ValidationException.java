package com.prueba.firmadigital.domain;

public class ValidationException extends RuntimeException{

    public ValidationException(String message) {
        super(message);
    }
}
