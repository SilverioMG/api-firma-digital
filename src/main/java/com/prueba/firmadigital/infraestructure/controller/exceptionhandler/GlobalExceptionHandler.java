package com.prueba.firmadigital.infraestructure.controller.exceptionhandler;

import com.prueba.firmadigital.domain.ValidationException;
import com.prueba.firmadigital.infraestructure.controller.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<Void>> defaultHandleExcpetion(Exception ex) {
        ResponseDto<Void> response = new ResponseDto<>(false, ex.getMessage(), null);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ResponseDto<Void>> handleValidationException(ValidationException ex) {
        ResponseDto<Void> response = new ResponseDto<>(false, ex.getMessage(), null);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
