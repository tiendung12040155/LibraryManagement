package com.example.demo.service.impl;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.UserStatus;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.service.AuthService;
import com.example.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtils jwtUtils;
    
    @Override
    public AuthResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        
        return new AuthResponse(jwt, userDetails.getId(), userDetails.getUsername(), 
                userDetails.getEmail(), userDetails.getFullName(), roles);
    }
    
    @Override
    @Transactional
    public AuthResponse registerUser(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new DuplicateResourceException("Error: Username is already taken!");
        }
        
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new DuplicateResourceException("Error: Email is already in use!");
        }
        
        if (registerRequest.getEmployeeCode() != null && 
            userRepository.existsByEmployeeCode(registerRequest.getEmployeeCode())) {
            throw new DuplicateResourceException("Error: Employee code is already in use!");
        }
        
        // Create new user's account
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFullName(registerRequest.getFullName());
        user.setPhone(registerRequest.getPhone());
        user.setAddress(registerRequest.getAddress());
        user.setPosition(registerRequest.getPosition());
        user.setEmployeeCode(registerRequest.getEmployeeCode());
        user.setStatus(UserStatus.ACTIVE);
        
        Set<String> strRoles = registerRequest.getRoles();
        Set<Role> roles = new HashSet<>();
        
        if (strRoles == null || strRoles.isEmpty()) {
            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "librarian":
                        Role librarianRole = roleRepository.findByName("ROLE_LIBRARIAN")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(librarianRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName("ROLE_USER")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        
        user.setRoles(roles);
        User savedUser = userRepository.save(user);
        
        // Generate token for the newly registered user
        String jwt = jwtUtils.generateTokenFromUsername(savedUser.getUsername());
        List<String> roleNames = savedUser.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());
        
        return new AuthResponse(jwt, savedUser.getId(), savedUser.getUsername(), 
                savedUser.getEmail(), savedUser.getFullName(), roleNames);
    }
    
    @Override
    @Transactional
    public void initializeRoles() {
        // Initialize roles if they don't exist
        if (!roleRepository.existsByName("ROLE_USER")) {
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            userRole.setDescription("Default role for all users");
            roleRepository.save(userRole);
        }
        
        if (!roleRepository.existsByName("ROLE_LIBRARIAN")) {
            Role librarianRole = new Role();
            librarianRole.setName("ROLE_LIBRARIAN");
            librarianRole.setDescription("Role for librarians");
            roleRepository.save(librarianRole);
        }
        
        if (!roleRepository.existsByName("ROLE_ADMIN")) {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            adminRole.setDescription("Role for administrators");
            roleRepository.save(adminRole);
        }
    }
}
