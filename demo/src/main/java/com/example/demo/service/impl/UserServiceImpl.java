package com.example.demo.service.impl;

import com.example.demo.dto.SearchUserRequest;
import com.example.demo.dto.UserProfileRequest;
import com.example.demo.dto.UserProfileResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserProfileResponse getCurrentUserProfile() {
        Long currentUserId = UserUtils.getCurrentUserId();
        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + currentUserId));
        return toDto(user);
    }

    @Override
    @Transactional
    public UserProfileResponse updateUserProfile(UserProfileRequest request) {
        Long currentUserId = UserUtils.getCurrentUserId();
        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + currentUserId));

        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setPosition(request.getPosition());
        userRepository.save(user);
        return toDto(user);
    }

    @Override
    public List<UserProfileResponse> searchUsers(SearchUserRequest request) {
        return userRepository.searchUsers(request.getFullName(), request.getAddress());
    }

    /**
     * Convert User entity to UserProfileResponse DTO
     * @param user User entity to convert
     * @return UserProfileResponse DTO
     */
    private UserProfileResponse toDto(User user) {
        UserProfileResponse response = new UserProfileResponse();
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());
        response.setPhone(user.getPhone());
        response.setAddress(user.getAddress());
        response.setPosition(user.getPosition());
        return response;
    }

}
