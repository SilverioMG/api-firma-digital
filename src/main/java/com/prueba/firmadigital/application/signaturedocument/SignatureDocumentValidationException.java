package com.prueba.firmadigital.application.signaturedocument;

import com.prueba.firmadigital.domain.ValidationException;

public class SignatureDocumentValidationException extends ValidationException {

    public SignatureDocumentValidationException(String message) {
        super(message);
    }
}
