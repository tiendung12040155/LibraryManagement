package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TestController {
    
    /**
     * Public endpoint accessible by everyone
     * @return public content message
     */
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }
    
    /**
     * User endpoint accessible by users, librarians and admins
     * @return user content message
     */
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('LIBRARIAN') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }
    
    /**
     * Librarian endpoint accessible by librarians and admins
     * @return librarian content message
     */
    @GetMapping("/librarian")
    @PreAuthorize("hasRole('LIBRARIAN') or hasRole('ADMIN')")
    public String librarianAccess() {
        return "Librarian Content.";
    }
    
    /**
     * Admin endpoint accessible by admins only
     * @return admin content message
     */
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Content.";
    }
}
