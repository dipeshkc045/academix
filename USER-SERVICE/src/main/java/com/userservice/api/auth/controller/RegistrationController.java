package com.userservice.api.auth.controller;

import com.userservice.api.auth.model.request.RegistrationRequestDTO;
import com.userservice.api.auth.service.RegistrationService;
import com.userservice.common.ServiceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Registration", description = "User registration management APIs")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Operation(
            summary = "Register a new user",
            description = "Register a new user (Student, Teacher, or Staff) with the provided details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "User registered successfully",
                    content = @Content(schema = @Schema(implementation = ServiceResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = ServiceResponse.class))
            )
    })
    @PostMapping("/register")
    public ResponseEntity<ServiceResponse<Void>> registerUser(@RequestBody RegistrationRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationService.registerUser(request));
    }
} 