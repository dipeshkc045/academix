package com.authservice.service;

import com.authservice.model.LoginResponse;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
public class KeycloakAuthService {
    @Value("${keycloak.auth-server-url}")
    private String keycloakUrl;
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.resource}")
    private String clientId;
    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    public LoginResponse login(String username, String password) {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(keycloakUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType(OAuth2Constants.PASSWORD)
                .username(username)
                .password(password)
                .build();
        try {
            AccessTokenResponse tokenResponse = keycloak.tokenManager().getAccessToken();
            return new LoginResponse(tokenResponse.getToken(), tokenResponse.getRefreshToken(), null);
        } catch (Exception e) {
            return new LoginResponse(null, null, "Invalid credentials or Keycloak error");
        }
    }

    public boolean userExists(String username) {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(keycloakUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();
        try {
            UsersResource usersResource = keycloak.realm(realm).users();
            return !usersResource.search(username).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
} 