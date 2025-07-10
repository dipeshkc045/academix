package com.userservice.api.user.service;

import com.userservice.api.user.mapper.UserMapper;
import com.userservice.api.user.model.request.CreateUserRequest;
import com.userservice.api.user.model.request.UpdateUserRequest;
import com.userservice.api.user.model.response.UserResponse;
import com.userservice.api.user.model.table.User;
import com.userservice.api.user.repository.UserRepository;
import com.userservice.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse createUser(CreateUserRequest request) {
        log.info("Creating new user with username: {}", request.getUsername());

        // Check if username or email already exists using MyBatis
        if (userMapper.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userMapper.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .role(request.getRole())
                .status(User.UserStatus.ACTIVE)
                .build();

        // Use JPA for write operation
        User savedUser = userRepository.save(user);
        log.info("User created successfully with ID: {}", savedUser.getId());
        
        return UserResponse.fromUser(savedUser);
    }

    public UserResponse getUserById(UUID id) {
        log.info("Fetching user with ID: {}", id);
        User user = userMapper.selectUserById(id);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
        return UserResponse.fromUser(user);
    }

    public UserResponse getUserByUsername(String username) {
        log.info("Fetching user with username: {}", username);
        User user = userMapper.selectUserByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with username: " + username);
        }
        return UserResponse.fromUser(user);
    }

    public List<UserResponse> getAllUsers() {
        log.info("Fetching all users");
        return userMapper.selectAllUsers().stream()
                .map(UserResponse::fromUser)
                .collect(Collectors.toList());
    }

    public List<UserResponse> getUsersByRole(User.UserRole role) {
        log.info("Fetching users with role: {}", role);
        return userMapper.selectUsersByRole(role).stream()
                .map(UserResponse::fromUser)
                .collect(Collectors.toList());
    }

    public List<UserResponse> getUsersByStatus(User.UserStatus status) {
        log.info("Fetching users with status: {}", status);
        return userMapper.selectUsersByStatus(status).stream()
                .map(UserResponse::fromUser)
                .collect(Collectors.toList());
    }

    public List<UserResponse> searchUsers(String keyword) {
        log.info("Searching users with keyword: {}", keyword);
        return userMapper.searchUsers(keyword).stream()
                .map(UserResponse::fromUser)
                .collect(Collectors.toList());
    }

    public UserResponse updateUser(UUID id, UpdateUserRequest request) {
        log.info("Updating user with ID: {}", id);
        User user = userMapper.selectUserById(id);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }

        // Update fields if provided
        if (request.getUsername() != null && !request.getUsername().equals(user.getUsername())) {
            if (userMapper.existsByUsername(request.getUsername())) {
                throw new IllegalArgumentException("Username already exists");
            }
            user.setUsername(request.getUsername());
        }

        if (request.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        if (request.getEmail() != null && !request.getEmail().equals(user.getEmail())) {
            if (userMapper.existsByEmail(request.getEmail())) {
                throw new IllegalArgumentException("Email already exists");
            }
            user.setEmail(request.getEmail());
        }

        if (request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }

        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }

        if (request.getPhoneNumber() != null) {
            user.setPhoneNumber(request.getPhoneNumber());
        }

        if (request.getStatus() != null) {
            user.setStatus(request.getStatus());
        }

        // Use JPA for write operation
        User updatedUser = userRepository.save(user);
        log.info("User updated successfully with ID: {}", updatedUser.getId());
        
        return UserResponse.fromUser(updatedUser);
    }

    public void deleteUser(UUID id) {
        log.info("Deleting user with ID: {}", id);
        User user = userMapper.selectUserById(id);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
        
        user.setStatus(User.UserStatus.DELETED);
        // Use JPA for write operation
        userRepository.save(user);
        log.info("User deleted successfully with ID: {}", id);
    }

    public void updateLastLogin(UUID userId) {
        User user = userMapper.selectUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with ID: " + userId);
        }
        user.setLastLogin(LocalDateTime.now());
        // Use JPA for write operation
        userRepository.save(user);
    }

    public boolean existsByUsername(String username) {
        return userMapper.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userMapper.existsByEmail(email);
    }
} 