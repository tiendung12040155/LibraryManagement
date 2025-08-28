package com.example.demo.controller;

import com.example.demo.dto.AuthorCreateRequest;
import com.example.demo.dto.AuthorResponse;
import com.example.demo.dto.AuthorUpdateRequest;
import com.example.demo.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthorController {
    
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    /**
     * Get all authors
     * @return list of all authors
     */
    @GetMapping
    public ResponseEntity<List<AuthorResponse>> getAllAuthors() {
        List<AuthorResponse> responseList = authorService.getAllAuthors();
        return ResponseEntity.ok(responseList);
    }

    /**
     * Get author by ID
     * @param id author ID
     * @return author details
     */
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable Long id) {
        AuthorResponse response = authorService.getAuthorById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Create a new author
     * @param request author creation data
     * @return created author details
     */
    @PostMapping
    @PreAuthorize("hasRole('LIBRARIAN') or hasRole('ADMIN')")
    public ResponseEntity<AuthorResponse> createAuthor(@Valid @RequestBody AuthorCreateRequest request) {
        AuthorResponse response = authorService.createAuthor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Update an existing author
     * @param id author ID
     * @param request author update data
     * @return updated author details
     */
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN') or hasRole('ADMIN')")
    public ResponseEntity<AuthorResponse> updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorUpdateRequest request) {
        AuthorResponse response = authorService.updateAuthor(id, request);
        return ResponseEntity.ok(response);
    }
}
