package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Get all books
     * @return list of all books
     */
    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookResponse> responses = bookService.getAllBooks();
        return ResponseEntity.ok(responses);
    }

    /**
     * Get book by ID
     * @param id book ID
     * @return book details
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        BookResponse response = bookService.getBookById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Create a new book
     * @param request book creation data
     * @return created book details
     */
    @PostMapping
    @PreAuthorize("hasRole('LIBRARIAN') or hasRole('ADMIN')")
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookCreateRequest request) {
        BookResponse created = bookService.createBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Update an existing book
     * @param id book ID
     * @param request book update data
     * @return updated book details
     */
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN') or hasRole('ADMIN')")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @Valid @RequestBody BookUpdateRequest request) {
        BookResponse updated = bookService.updateBook(id, request);
        return ResponseEntity.ok(updated);
    }

    /**
     * Delete a book
     * @param id book ID
     * @return no content response
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Borrow a book
     * @param request borrow book request data
     * @return borrow operation result
     */
    @PatchMapping("/borrow")
    @PreAuthorize("hasRole('LIBRARIAN') or hasRole('ADMIN')")
    public ResponseEntity<BorrowBookResponse> borrowBook(@Valid @RequestBody BorrowBookRequest request) {
        BorrowBookResponse response = bookService.borrowBook(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Return a book
     * @param request return book request data
     * @return return operation result
     */
    @PatchMapping("/return")
    @PreAuthorize("hasRole('LIBRARIAN') or hasRole('ADMIN')")
    public ResponseEntity<BorrowBookResponse> returnBook(@Valid @RequestBody BorrowBookRequest request) {
        BorrowBookResponse response = bookService.returnBook(request);
        return ResponseEntity.ok(response);
    }
}


