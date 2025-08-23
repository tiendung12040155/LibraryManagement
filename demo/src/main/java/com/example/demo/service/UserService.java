package com.example.demo.service;

import com.example.demo.dto.UserProfileRequest;
import com.example.demo.dto.UserProfileResponse;

public interface UserService {
    
    /**
     * Get profile information of the currently logged-in user
     * @return UserProfileResponse containing user profile information
     */
    UserProfileResponse getCurrentUserProfile();

    UserProfileResponse updateUserProfile(UserProfileRequest request);
}
