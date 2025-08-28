package com.example.demo.service;

import com.example.demo.dto.*;

import java.util.List;

/**
 * Service interface for book operations
 */
public interface BookService {
    
    /**
     * Get all books
     * @return list of all books
     */
    List<BookResponse> getAllBooks();
    
    /**
     * Get book by ID
     * @param id book ID
     * @return book details
     */
    BookResponse getBookById(Long id);
    
    /**
     * Create a new book
     * @param request book creation data
     * @return created book details
     */
    BookResponse createBook(BookCreateRequest request);

    /**
     * Update an existing book
     * @param id book ID
     * @param request book update data
     * @return updated book details
     */
    BookResponse updateBook(Long id, BookUpdateRequest request);

    /**
     * Delete a book
     * @param id book ID
     */
    void deleteBook(Long id);
    
    /**
     * Get all books borrowed by a specific user
     * @param borrowerId ID of the user who borrowed the books
     * @return list of borrowed books
     */
    List<BorrowedBookResponse> getBooksByBorrowerId(Long borrowerId);

    /**
     * Borrow a book
     * @param request borrow book request data
     * @return borrow operation result
     */
    BorrowBookResponse borrowBook(BorrowBookRequest request);

    /**
     * Return a book
     * @param request return book request data
     * @return return operation result
     */
    BorrowBookResponse returnBook(BorrowBookRequest request);
}


