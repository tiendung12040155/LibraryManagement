package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateRequest {

    @NotNull(message = "Author id is required")
    private Long authorId;

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must be at most 255 characters")
    private String title;

    @Size(max = 20, message = "ISBN must be at most 20 characters")
    private String isbn;

    @Size(max = 255, message = "Publisher must be at most 255 characters")
    private String publisher;

    private LocalDate publishedDate;

    private String description;

    private String language;

    private List<Long> categoryIds;
}


