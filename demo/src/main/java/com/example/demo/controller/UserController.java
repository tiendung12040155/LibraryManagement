package com.example.demo.controller;

import com.example.demo.dto.BorrowedBookResponse;
import com.example.demo.dto.SearchUserRequest;
import com.example.demo.dto.UserProfileRequest;
import com.example.demo.dto.UserProfileResponse;
import com.example.demo.service.BookService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final UserService userService;
    private final BookService bookService;

    public UserController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    /**
     * Get profile information of the currently logged-in user
     * @return profile information of the current user
     */
    @GetMapping("/profile")
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public ResponseEntity<UserProfileResponse> getCurrentUserProfile() {
        UserProfileResponse profile = userService.getCurrentUserProfile();
        return ResponseEntity.ok(profile);
    }

    /**
     * Update profile information of the currently logged-in user
     * @param request user profile update data
     * @return updated profile information of the current user
     */
    @PatchMapping("/profile")
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public ResponseEntity<UserProfileResponse> updateUserProfile(@Valid @RequestBody UserProfileRequest request) {
        UserProfileResponse response = userService.updateUserProfile(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Get all books borrowed by a specific user
     * @param userId ID of the user to get borrowed books for
     * @return list of books borrowed by the user
     */
    @GetMapping("/{userId}/borrowed-books")
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public ResponseEntity<List<BorrowedBookResponse>> getBorrowedBooksByUserId(@PathVariable Long userId) {
        List<BorrowedBookResponse> borrowedBooks = bookService.getBooksByBorrowerId(userId);
        return ResponseEntity.ok(borrowedBooks);
    }

    /**
     * Search users based on criteria
     * @param request search criteria
     * @return list of users matching the criteria
     */
    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    public ResponseEntity<List<UserProfileResponse>> searchUsers(@Valid @RequestBody SearchUserRequest request) {
        List<UserProfileResponse> responses = userService.searchUsers(request);
        return ResponseEntity.ok(responses);
    }
}
