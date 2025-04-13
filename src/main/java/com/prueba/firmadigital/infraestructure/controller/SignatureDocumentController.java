package com.prueba.firmadigital.infraestructure.controller;

import com.prueba.firmadigital.application.signaturedocument.SignatureDocumentCommand;
import com.prueba.firmadigital.application.signaturedocument.SignatureDocumentQuery;
import com.prueba.firmadigital.application.signaturedocument.SignatureDocumentUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signaturedocument")
public class SignatureDocumentController {

    private final SignatureDocumentUseCase signDocumentUseCase;

    public SignatureDocumentController(SignatureDocumentUseCase signDocumentUseCase) {
        this.signDocumentUseCase = signDocumentUseCase;
    }

    @PostMapping()
    public ResponseEntity<ResponseDto<SignatureDocumentQuery>> signDocument(@RequestBody SignatureDocumentCommand command) {
        SignatureDocumentQuery result = signDocumentUseCase.signDocument(command);
        ResponseDto<SignatureDocumentQuery> response = new ResponseDto<>(true, "Firma Digital del documento generada.", result);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
