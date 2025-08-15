package com.example.demo.entity;

public enum UserStatus {
    ACTIVE("ACTIVE", "Hoạt động"),
    INACTIVE("INACTIVE", "Không hoạt động");
    
    private final String code;
    private final String description;
    
    UserStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getDescription() {
        return description;
    }
} 