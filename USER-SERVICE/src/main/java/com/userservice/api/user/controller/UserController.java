package com.userservice.api.user.controller;

import com.userservice.api.user.model.request.CreateUserRequest;
import com.userservice.api.user.model.request.UpdateUserRequest;
import com.userservice.api.user.model.response.UserResponse;
import com.userservice.api.user.model.table.User;
import com.userservice.api.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Create a new user", description = "Creates a new user with the provided information")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        log.info("Received request to create user: {}", request.getUsername());
        UserResponse response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieves a user by their ID")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
        log.info("Received request to get user by ID: {}", id);
        UserResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/username/{username}")
    @Operation(summary = "Get user by username", description = "Retrieves a user by their username")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) {
        log.info("Received request to get user by username: {}", username);
        UserResponse response = userService.getUserByUsername(username);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieves all users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        log.info("Received request to get all users");
        List<UserResponse> response = userService.getAllUsers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/role/{role}")
    @Operation(summary = "Get users by role", description = "Retrieves all users with the specified role")
    public ResponseEntity<List<UserResponse>> getUsersByRole(@PathVariable User.UserRole role) {
        log.info("Received request to get users by role: {}", role);
        List<UserResponse> response = userService.getUsersByRole(role);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get users by status", description = "Retrieves all users with the specified status")
    public ResponseEntity<List<UserResponse>> getUsersByStatus(@PathVariable User.UserStatus status) {
        log.info("Received request to get users by status: {}", status);
        List<UserResponse> response = userService.getUsersByStatus(status);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    @Operation(summary = "Search users", description = "Searches users by keyword")
    public ResponseEntity<List<UserResponse>> searchUsers(@RequestParam String keyword) {
        log.info("Received request to search users with keyword: {}", keyword);
        List<UserResponse> response = userService.searchUsers(keyword);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user", description = "Updates an existing user")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @Valid @RequestBody UpdateUserRequest request) {
        log.info("Received request to update user with ID: {}", id);
        UserResponse response = userService.updateUser(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user", description = "Soft deletes a user by setting status to DELETED")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        log.info("Received request to delete user with ID: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/check-username")
    @Operation(summary = "Check username availability", description = "Checks if a username is available")
    public ResponseEntity<Boolean> checkUsernameAvailability(@RequestParam String username) {
        log.info("Received request to check username availability: {}", username);
        boolean isAvailable = !userService.existsByUsername(username);
        return ResponseEntity.ok(isAvailable);
    }

    @GetMapping("/check-email")
    @Operation(summary = "Check email availability", description = "Checks if an email is available")
    public ResponseEntity<Boolean> checkEmailAvailability(@RequestParam String email) {
        log.info("Received request to check email availability: {}", email);
        boolean isAvailable = !userService.existsByEmail(email);
        return ResponseEntity.ok(isAvailable);
    }
} 