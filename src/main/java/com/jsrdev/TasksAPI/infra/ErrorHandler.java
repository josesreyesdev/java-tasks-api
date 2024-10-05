package com.jsrdev.TasksAPI.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    // not found
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handlerError404() {
        return ResponseEntity.notFound().build();
    }

    // bad request
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DataErrorValidation>> handlerError400 (MethodArgumentNotValidException ex) {
        List<DataErrorValidation> errors = ex.getFieldErrors()
                .stream()
                .map(DataErrorValidation::new)
                .toList();

        return ResponseEntity.badRequest().body(errors);
    }

    private record DataErrorValidation(String field, String error) {

        public DataErrorValidation(FieldError fieldError) {
            this(
                    fieldError.getField(),
                    fieldError.getDefaultMessage()
            );
        }
    }
}
