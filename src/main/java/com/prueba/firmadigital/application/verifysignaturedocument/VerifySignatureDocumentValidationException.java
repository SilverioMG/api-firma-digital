package com.prueba.firmadigital.application.verifysignaturedocument;

import com.prueba.firmadigital.domain.ValidationException;

public class VerifySignatureDocumentValidationException extends ValidationException {

    public VerifySignatureDocumentValidationException(String message) {
        super(message);
    }
}
