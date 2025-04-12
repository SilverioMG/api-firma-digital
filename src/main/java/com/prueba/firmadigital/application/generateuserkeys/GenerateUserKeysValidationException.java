package com.prueba.firmadigital.application.generateuserkeys;

import com.prueba.firmadigital.domain.ValidationException;

public class GenerateUserKeysValidationException extends ValidationException {

    GenerateUserKeysValidationException(String message) {
        super(message);
    }
}
