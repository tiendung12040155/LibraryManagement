package com.example.demo.service;

import com.example.demo.dto.BookCreateRequest;
import com.example.demo.dto.BookResponse;
import com.example.demo.dto.BookUpdateRequest;
import com.example.demo.dto.BorrowedBookResponse;

import java.util.List;

public interface BookService {
    BookResponse createBook(BookCreateRequest request);

    BookResponse updateBook(Long id, BookUpdateRequest request);

    void deleteBook(Long id);

    BookResponse getBookById(Long id);

    List<BookResponse> getAllBooks();
    
    /**
     * Get all books borrowed by a specific user
     * @param borrowerId ID of the user who borrowed the books
     * @return List of borrowed books
     */
    List<BorrowedBookResponse> getBooksByBorrowerId(Long borrowerId);
}


