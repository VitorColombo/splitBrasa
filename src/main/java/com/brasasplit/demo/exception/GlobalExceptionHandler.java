package com.brasasplit.demo.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice //informa pro spring que essa classe vai tratar excecoes globalmente
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class) //pega pra tratar os erros de validacao nos DTOs
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        // o servidor retorna que nao conseguiu processar a requisicao
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errors);
    }
}