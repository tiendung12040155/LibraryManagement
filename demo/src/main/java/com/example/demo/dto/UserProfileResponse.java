package com.example.demo.dto;

import com.example.demo.entity.User;
import com.example.demo.entity.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {
    private String username;
    private String email;
    private String fullName;
    private String phone;
    private String address;
    private String position;

}
