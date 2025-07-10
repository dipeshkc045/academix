package com.userservice.api.auth.model.request;

import com.userservice.common.designation.Designation;
import com.userservice.common.user.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Registration request data")
public class RegistrationRequestDTO {
    @Schema(description = "First name of the user", example = "John")
    private String firstName;

    @Schema(description = "Last name of the user", example = "Doe")
    private String lastName;

    @Schema(description = "Email address of the user", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Password for the user account", example = "securepassword")
    private String password;

    @Schema(description = "Phone number of the user", example = "1234567890")
    private String phoneNumber;

    @Schema(description = "Address of the user", example = "123 Main St")
    private String address;

    @Schema(description = "Department name (required for teachers and staff)", example = "Computer Science")
    private String department;

    @Schema(description = "Qualification details (required for teachers and staff)", example = "PhD")
    private String qualification;

    @Schema(description = "Designation (required for teachers and staff)", example = "PROFESSOR")
    private Designation designation;

    @Schema(description = "Type of user being registered", example = "STUDENT")
    private UserType userType;
} 