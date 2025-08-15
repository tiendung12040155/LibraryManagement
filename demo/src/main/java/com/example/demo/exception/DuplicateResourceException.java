package com.example.demo.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when trying to create a resource that already exists
 */
public class DuplicateResourceException extends BaseException {
    
    public DuplicateResourceException(String resourceName, String fieldName, Object fieldValue) {
        super(
            HttpStatus.CONFLICT,
            "DUPLICATE_RESOURCE",
            String.format("%s already exists with %s: %s", resourceName, fieldName, fieldValue)
        );
    }
    
    public DuplicateResourceException(String message) {
        super(HttpStatus.CONFLICT, "DUPLICATE_RESOURCE", message);
    }
    
    public DuplicateResourceException(String message, Throwable cause) {
        super(HttpStatus.CONFLICT, "DUPLICATE_RESOURCE", message, cause);
    }
} 