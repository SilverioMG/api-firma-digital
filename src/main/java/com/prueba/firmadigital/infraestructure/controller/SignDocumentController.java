package com.prueba.firmadigital.infraestructure.controller;

import com.prueba.firmadigital.application.signdocument.SignDocumentCommand;
import com.prueba.firmadigital.application.signdocument.SignDocumentQuery;
import com.prueba.firmadigital.application.signdocument.SignDocumentUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signdocument")
public class SignDocumentController {

    private final SignDocumentUseCase signDocumentUseCase;

    public SignDocumentController(SignDocumentUseCase signDocumentUseCase) {
        this.signDocumentUseCase = signDocumentUseCase;
    }

    @PostMapping()
    public ResponseEntity<ResponseDto<SignDocumentQuery>> signDocument(@RequestBody SignDocumentCommand command) {
        SignDocumentQuery result = signDocumentUseCase.signDocument(command);
        ResponseDto<SignDocumentQuery> response = new ResponseDto<>(true, "Firma Digital del documento generada.", result);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
