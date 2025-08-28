package com.example.demo.service;

import com.example.demo.dto.SearchUserRequest;
import com.example.demo.dto.UserProfileRequest;
import com.example.demo.dto.UserProfileResponse;

import java.util.List;

/**
 * Service interface for user operations
 */
public interface UserService {
    
    /**
     * Get profile information of the currently logged-in user
     * @return user profile information
     */
    UserProfileResponse getCurrentUserProfile();

    /**
     * Update profile information of the currently logged-in user
     * @param request user profile update data
     * @return updated user profile information
     */
    UserProfileResponse updateUserProfile(UserProfileRequest request);

    /**
     * Search users based on criteria
     * @param request search criteria
     * @return list of users matching the criteria
     */
    List<UserProfileResponse> searchUsers(SearchUserRequest request);
}
