package com.example.demo.service.impl;

import com.example.demo.dto.AuthorCreateRequest;
import com.example.demo.dto.AuthorResponse;
import com.example.demo.dto.AuthorUpdateRequest;
import com.example.demo.entity.Author;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorResponse createAuthor(AuthorCreateRequest request) {
        Author author = new Author();
        author.setName(request.getName());
        author.setBiography(request.getBiography());
        author.setBirthDate(request.getBirthDate());
        author.setNationality(request.getNationality());
        authorRepository.save(author);

        return toAuthorResponse(author);
    }

    @Override
    public AuthorResponse updateAuthor(Long id, AuthorUpdateRequest request) {
        //validate id
        Author author = authorRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Not found author!!!!"));
        //edit author
        if (request.getName() != null) {
            author.setName(request.getName());
        }
        if (request.getBiography() != null) {
            author.setBiography(request.getBiography());
        }
        if (request.getBirthDate() != null) {
            author.setBirthDate(request.getBirthDate());
        }
        if (request.getNationality() != null) {
            author.setNationality(request.getNationality());
        }

        authorRepository.save(author);

        return toAuthorResponse(author);
    }

    @Override
    public List<AuthorResponse> getAllAuthors() {
        List<Author> listFound = authorRepository.findAll();
        List<AuthorResponse> responseList = listFound.stream().map(this::toAuthorResponse).toList();
        return responseList;
    }

    @Override
    public AuthorResponse getAuthorById(Long id) {
        //validate id
        Author author = authorRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Not found author!!!!"));
        return toAuthorResponse(author);
    }

    private AuthorResponse toAuthorResponse(Author author) {
        return new AuthorResponse(
                author.getId(),
                author.getName(),
                author.getBiography(),
                author.getBirthDate(),
                author.getNationality()
        );
    }
}
