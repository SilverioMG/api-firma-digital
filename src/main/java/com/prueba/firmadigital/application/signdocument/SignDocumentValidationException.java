package com.prueba.firmadigital.application.signdocument;

import com.prueba.firmadigital.domain.ValidationException;

public class SignDocumentValidationException extends ValidationException {

    public SignDocumentValidationException(String message) {
        super(message);
    }
}
