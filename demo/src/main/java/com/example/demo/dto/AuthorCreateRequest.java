package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthorCreateRequest {
    @NotBlank(message = "Name is required")
    private String name;

    private String biography;

    private LocalDate birthDate;

    private String nationality;
}
