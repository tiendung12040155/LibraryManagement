package com.example.demo.service.impl;

import com.example.demo.dto.BookCreateRequest;
import com.example.demo.dto.BookResponse;
import com.example.demo.dto.BookUpdateRequest;
import com.example.demo.dto.BorrowedBookResponse;
import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.entity.Category;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public BookResponse createBook(BookCreateRequest request) {
        if (request.getIsbn() != null && !request.getIsbn().isEmpty() && bookRepository.existsByIsbn(request.getIsbn())) {
            throw new DuplicateResourceException("Book", "isbn", request.getIsbn());
        }

        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", request.getAuthorId()));

        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setPublisher(request.getPublisher());
        book.setPublishedDate(request.getPublishedDate());
        book.setDescription(request.getDescription());
        book.setLanguage(request.getLanguage());

        if (request.getCategoryIds() != null && !request.getCategoryIds().isEmpty()) {
            List<Category> categories = categoryRepository.findAllById(request.getCategoryIds());
            book.setCategories(new ArrayList<>(categories));
        }

        Book saved = bookRepository.save(book);
        return toBookResponse(saved);
    }

    @Override
    public BookResponse updateBook(Long id, BookUpdateRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));

        if (request.getAuthorId() != null) {
            Author author = authorRepository.findById(request.getAuthorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Author", "id", request.getAuthorId()));
            book.setAuthor(author);
        }

        if (request.getTitle() != null) {
            book.setTitle(request.getTitle());
        }

        if (request.getIsbn() != null) {
            if (!Objects.equals(request.getIsbn(), book.getIsbn()) && request.getIsbn().length() > 0 && bookRepository.existsByIsbn(request.getIsbn())) {
                throw new DuplicateResourceException("Book", "isbn", request.getIsbn());
            }
            book.setIsbn(request.getIsbn());
        }

        if (request.getPublisher() != null) {
            book.setPublisher(request.getPublisher());
        }

        if (request.getPublishedDate() != null) {
            book.setPublishedDate(request.getPublishedDate());
        }

        if (request.getDescription() != null) {
            book.setDescription(request.getDescription());
        }

        if (request.getLanguage() != null) {
            book.setLanguage(request.getLanguage());
        }

        if (request.getCategoryIds() != null) {
            List<Category> categories = categoryRepository.findAllById(request.getCategoryIds());
            book.setCategories(new ArrayList<>(categories));
        }

        Book saved = bookRepository.save(book);
        return toBookResponse(saved);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        bookRepository.delete(book);
    }

    @Override
    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findByIdWithAuthorAndCategories(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        return toBookResponse(book);
    }

    @Override
    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::toBookResponse).collect(Collectors.toList());
    }

    @Override
    public List<BorrowedBookResponse> getBooksByBorrowerId(Long borrowerId) {
        List<Book> books = bookRepository.findByBorrowerIdWithAuthorAndCategories(borrowerId);
        return books.stream()
                .map(BorrowedBookResponse::toListBookBorrow)
                .collect(Collectors.toList());
    }

    private BookResponse toBookResponse(Book book) {
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        if (book.getAuthor() != null) {
            response.setAuthorId(book.getAuthor().getId());
            response.setAuthorName(book.getAuthor().getName());
        }
        response.setTitle(book.getTitle());
        response.setIsbn(book.getIsbn());
        response.setPublisher(book.getPublisher());
        response.setPublishedDate(book.getPublishedDate());
        response.setDescription(book.getDescription());
        response.setLanguage(book.getLanguage());
        response.setCreatedAt(book.getCreatedAt());
        response.setUpdatedAt(book.getUpdatedAt());

        if (book.getCategories() != null) {
            response.setCategoryIds(book.getCategories().stream().map(Category::getId).collect(Collectors.toList()));
            response.setCategoryNames(book.getCategories().stream().map(Category::getName).collect(Collectors.toList()));
        }
        return response;
    }


}


