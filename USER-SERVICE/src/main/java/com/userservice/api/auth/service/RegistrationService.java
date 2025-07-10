package com.userservice.api.auth.service;

import com.userservice.api.auth.model.request.RegistrationRequestDTO;
import com.userservice.common.ServiceResponse;

public interface RegistrationService {
    ServiceResponse<Void> registerUser(RegistrationRequestDTO registrationRequest);
} 