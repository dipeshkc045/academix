package com.authservice.controller;

import com.authservice.model.LoginRequest;
import com.authservice.model.LoginResponse;
import com.authservice.model.PreLoginRequest;
import com.authservice.model.PreLoginResponse;
import com.authservice.service.KeycloakAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final KeycloakAuthService keycloakAuthService;

    public AuthController(KeycloakAuthService keycloakAuthService) {
        this.keycloakAuthService = keycloakAuthService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = keycloakAuthService.login(request.getUsername(), request.getPassword());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(new LoginResponse(null, null, "Invalid credentials or Keycloak error"));
        }
    }

    @PostMapping("/prelogin")
    public ResponseEntity<PreLoginResponse> prelogin(@RequestBody PreLoginRequest request) {
        boolean exists = keycloakAuthService.userExists(request.getUsername());
        return ResponseEntity.ok(new PreLoginResponse(exists));
    }
} 