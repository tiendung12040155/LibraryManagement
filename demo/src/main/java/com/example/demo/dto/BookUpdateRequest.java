package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookUpdateRequest {
    private Long authorId;
    private String title;
    private String isbn;
    private String publisher;
    private LocalDate publishedDate;
    private String description;
    private String language;
    private List<Long> categoryIds;
}


