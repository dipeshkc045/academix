package com.userservice.api.address.controller;


import com.userservice.api.address.model.request.AddressRequestDTO;
import com.userservice.api.address.model.response.AddressResponseDTO;
import com.userservice.api.address.service.AddressService;
import com.userservice.common.ServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<ServiceResponse<List<AddressResponseDTO>>> getAllAddresses() {
        ServiceResponse<List<AddressResponseDTO>> response = addressService.getAllAddresses();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse<AddressResponseDTO>> getAddressById(@PathVariable java.util.UUID id) {
        ServiceResponse<AddressResponseDTO> response = addressService.getAddressesById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ServiceResponse<AddressResponseDTO>> createAddress(
        @RequestBody AddressRequestDTO addressRequestDTO) {
        ServiceResponse<AddressResponseDTO> response = addressService.createAddresses(addressRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse<AddressResponseDTO>> updateAddress(
        @PathVariable java.util.UUID id,
        @RequestBody AddressRequestDTO addressRequestDTO) {
        ServiceResponse<AddressResponseDTO> response = addressService.updateAddresses(id, addressRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceResponse<Void>> deleteAddress(@PathVariable java.util.UUID id) {
        addressService.deleteAddress(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ServiceResponse<>());
    }
}

