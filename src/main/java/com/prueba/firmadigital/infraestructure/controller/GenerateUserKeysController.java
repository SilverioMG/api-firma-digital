package com.prueba.firmadigital.infraestructure.controller;

import com.prueba.firmadigital.application.generateuserkeys.GenerateUserKeysCommand;
import com.prueba.firmadigital.application.generateuserkeys.GenerateUserKeysUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/generatekeys")
public class GenerateUserKeysController {

    private final GenerateUserKeysUseCase generateUserKeysUseCase;

    public GenerateUserKeysController(GenerateUserKeysUseCase generateUserKeysUseCase) {
        this.generateUserKeysUseCase = generateUserKeysUseCase;
    }

    @PostMapping("/{userName}")
    public ResponseEntity<ResponseDto<Void>> generateUserKeys(@PathVariable(name = "userName") String userName) {
        GenerateUserKeysCommand command = new GenerateUserKeysCommand(userName);
        generateUserKeysUseCase.generateKeys(command);

        ResponseDto<Void> responseDto = new ResponseDto<>(true, "Claves generadas", null);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
