package com.example.demo.service;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;

/**
 * Service interface for authentication operations
 */
public interface AuthService {
    
    /**
     * Authenticate user with login credentials
     * @param loginRequest login credentials
     * @return authentication response with JWT token
     */
    AuthResponse authenticateUser(LoginRequest loginRequest);
    
    /**
     * Register new user
     * @param registerRequest user registration data
     * @return authentication response with JWT token
     */
    AuthResponse registerUser(RegisterRequest registerRequest);
    
    /**
     * Initialize default roles in the system
     */
    void initializeRoles();
}
