package com.example.demo.dto;

import com.example.demo.entity.UserStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileRequest {
    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "phone is required")
    private String phone;

    @NotBlank(message = "address is required")
    private String address;

    @NotBlank(message = "position is required")
    private String position;

}
