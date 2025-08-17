package com.example.demo.service.impl;

import com.example.demo.dto.AuthorCreateRequest;
import com.example.demo.dto.AuthorResponse;
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

        return new AuthorResponse(author.getName(), author.getBiography(), author.getBirthDate(), author.getNationality());
    }

    @Override
    public AuthorResponse updateAuthor(Long id, AuthorCreateRequest request) {
        //validate id
        Author author = authorRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Not found author!!!!"));
        //edit author
        author.setName(request.getName());
        author.setBiography(request.getBiography());
        author.setBirthDate(request.getBirthDate());
        author.setNationality(request.getNationality());

        authorRepository.save(author);

        return new AuthorResponse(author.getName(), author.getBiography(), author.getBirthDate(), author.getNationality());
    }

    @Override
    public List<AuthorResponse> getAllAuthor() {
        List<Author> listFound = authorRepository.findAll();
        List<AuthorResponse> responseList = listFound.stream().map(a ->
                new AuthorResponse(a.getName(), a.getBiography(), a.getBirthDate(), a.getNationality())).toList();
        return responseList;
    }

    @Override
    public AuthorResponse getAuthorById(Long id) {
        //validate id
        Author author = authorRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Not found author!!!!"));
        return new AuthorResponse(author.getName(), author.getBiography(), author.getBirthDate(), author.getNationality());
    }
}
