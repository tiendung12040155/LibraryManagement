package com.example.demo.utils;

import com.example.demo.security.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {
    
    /**
     * Get the ID of the currently logged-in user
     * @return ID of the current user
     * @throws RuntimeException if no user is logged in
     */
    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("No user is currently logged in");
        }
        
        Object principal = authentication.getPrincipal();
        
        if (principal instanceof UserDetailsImpl) {
            return ((UserDetailsImpl) principal).getId();
        }
        
        throw new RuntimeException("Unable to retrieve current user information");
    }
    
    /**
     * Get detailed information of the currently logged-in user
     * @return UserDetailsImpl of the current user
     * @throws RuntimeException if no user is logged in
     */
    public static UserDetailsImpl getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("No user is currently logged in");
        }
        
        Object principal = authentication.getPrincipal();
        
        if (principal instanceof UserDetailsImpl) {
            return (UserDetailsImpl) principal;
        }
        
        throw new RuntimeException("Unable to retrieve current user information");
    }
    
    /**
     * Check if any user is currently logged in
     * @return true if a user is logged in, false otherwise
     */
    public static boolean isUserLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && 
               authentication.isAuthenticated() && 
               authentication.getPrincipal() instanceof UserDetailsImpl;
    }
}
