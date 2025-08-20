package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Exception thrown when validation fails
 */
public class ValidationException extends BaseException {
    
    private final List<String> errors;
    
    public ValidationException(String message) {
        super(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", message);
        this.errors = List.of(message);
    }
    
    public ValidationException(String message, List<String> errors) {
        super(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", message);
        this.errors = errors;
    }
    
    public ValidationException(String message, Throwable cause) {
        super(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", message, cause);
        this.errors = List.of(message);
    }
    
    public List<String> getErrors() {
        return errors;
    }
} 