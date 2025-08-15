package com.example.demo.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when user is not authorized to access a resource
 */
public class UnauthorizedException extends BaseException {
    
    public UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", message);
    }
    
    public UnauthorizedException(String message, Throwable cause) {
        super(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", message, cause);
    }
    
    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", "Access denied. Please login to continue.");
    }
} 