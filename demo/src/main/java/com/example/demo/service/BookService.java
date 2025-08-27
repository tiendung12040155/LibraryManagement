package com.example.demo.service;

import com.example.demo.dto.*;

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

    BorrowBookResponse borrowBook(BorrowBookRequest request);

    BorrowBookResponse returnBook(BorrowBookRequest request);
}


