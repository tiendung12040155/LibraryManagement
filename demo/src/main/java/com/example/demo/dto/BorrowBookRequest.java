package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowBookRequest {
    @NotBlank(message = "borrowerId is required")
    private Long borrowerId;

    private List<Long> listBookIds;
}
