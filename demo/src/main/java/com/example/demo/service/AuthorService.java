package com.example.demo.service;

import com.example.demo.dto.AuthorCreateRequest;
import com.example.demo.dto.AuthorResponse;
import com.example.demo.dto.AuthorUpdateRequest;

import java.util.List;

/**
 * Service interface for author operations
 */
public interface AuthorService {
    
    /**
     * Get all authors
     * @return list of all authors
     */
    List<AuthorResponse> getAllAuthors();

    /**
     * Get author by ID
     * @param id author ID
     * @return author details
     */
    AuthorResponse getAuthorById(Long id);
    
    /**
     * Create a new author
     * @param request author creation data
     * @return created author details
     */
    AuthorResponse createAuthor(AuthorCreateRequest request);

    /**
     * Update an existing author
     * @param id author ID
     * @param request author update data
     * @return updated author details
     */
    AuthorResponse updateAuthor(Long id, AuthorUpdateRequest request);
}
