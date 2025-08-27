package com.example.demo.repository;

import com.example.demo.dto.UserProfileResponse;
import com.example.demo.entity.User;

import java.util.List;

public interface UserCriteriaRepository{
    List<UserProfileResponse> searchUsers(String name, String address);
}
