package com.example.demo.entity;

public enum RoleType {
    ADMIN("ADMIN", "Quản trị viên"),
    LIBRARIAN("LIBRARIAN", "Thủ thư"),
    USER("USER", "Người dùng");
    
    private final String code;
    private final String description;
    
    RoleType(String code, String description) {
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