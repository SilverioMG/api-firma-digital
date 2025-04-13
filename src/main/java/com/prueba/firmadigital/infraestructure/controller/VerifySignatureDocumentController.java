package com.prueba.firmadigital.infraestructure.controller;

import com.prueba.firmadigital.application.verifysignaturedocument.VerifySignatureDocumentCommand;
import com.prueba.firmadigital.application.verifysignaturedocument.VerifysignaturedocumentUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verifysignature")
public class VerifySignatureDocumentController {

    private final VerifysignaturedocumentUseCase verifysignaturedocumentUseCase;

    public VerifySignatureDocumentController(VerifysignaturedocumentUseCase verifysignaturedocumentUseCase) {
        this.verifysignaturedocumentUseCase = verifysignaturedocumentUseCase;
    }

    @PostMapping
    public ResponseEntity<ResponseDto<Void>> verifySignatureDocument(@RequestBody VerifySignatureDocumentCommand command) {
        boolean result = verifysignaturedocumentUseCase.verifySignatureDocument(command);
        ResponseDto<Void> response = (result) ? new ResponseDto<>(true, "Verificación correcta.", null)
                : new ResponseDto<>(false, "Verificación incorrecta.", null);
        HttpStatus httpStatus = (result) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(response, httpStatus);
    }
}
