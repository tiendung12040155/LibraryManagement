package com.example.demo.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when user is forbidden to access a resource
 */
public class ForbiddenException extends BaseException {
    
    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, "FORBIDDEN", message);
    }
    
    public ForbiddenException(String message, Throwable cause) {
        super(HttpStatus.FORBIDDEN, "FORBIDDEN", message, cause);
    }
    
    public ForbiddenException() {
        super(HttpStatus.FORBIDDEN, "FORBIDDEN", "You don't have permission to access this resource.");
    }
} 