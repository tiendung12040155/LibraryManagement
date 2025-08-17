package com.example.demo.service;

import com.example.demo.dto.AuthorCreateRequest;
import com.example.demo.dto.AuthorResponse;

import java.util.List;

public interface AuthorService {
    AuthorResponse createAuthor(AuthorCreateRequest request);

    AuthorResponse updateAuthor(Long id, AuthorCreateRequest request);

    List<AuthorResponse> getAllAuthor();

    AuthorResponse getAuthorById(Long id);
}
