package com.example.demo.service;

import com.example.demo.dto.AuthorCreateRequest;
import com.example.demo.dto.AuthorResponse;
import com.example.demo.dto.AuthorUpdateRequest;

import java.util.List;

public interface AuthorService {
    AuthorResponse createAuthor(AuthorCreateRequest request);

    AuthorResponse updateAuthor(Long id, AuthorUpdateRequest request);

    List<AuthorResponse> getAllAuthors();

    AuthorResponse getAuthorById(Long id);
}
