package com.example.demo.dto;

import com.example.demo.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowedBookResponse {

    private Long id;
    private String title;
    private String isbn;
    private String publisher;
    private LocalDate publishedDate;
    private String description;
    private String language;
    private Long borrowerId;
    private String authorName;

    /**
     * Convert Book entity to BorrowedBookResponse DTO
     *
     * @param book Book entity to convert
     * @return BorrowedBookResponse DTO
     */
    public static BorrowedBookResponse toListBookBorrow(Book book) {
        BorrowedBookResponse response = new BorrowedBookResponse();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setIsbn(book.getIsbn());
        response.setPublisher(book.getPublisher());
        response.setPublishedDate(book.getPublishedDate());
        response.setDescription(book.getDescription());
        response.setLanguage(book.getLanguage());
        response.setBorrowerId(book.getBorrowerId());

        // Set author name
        if (book.getAuthor() != null) {
            response.setAuthorName(book.getAuthor().getName());
        }

        return response;
    }
}
