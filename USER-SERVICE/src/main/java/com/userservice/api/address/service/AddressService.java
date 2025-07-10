package com.userservice.api.address.service;

import com.userservice.api.address.model.request.AddressRequestDTO;
import com.userservice.api.address.model.response.AddressResponseDTO;
import com.userservice.common.ServiceResponse;

import java.util.List;

public interface AddressService {


    ServiceResponse<List<AddressResponseDTO>> getAllAddresses();


    ServiceResponse<AddressResponseDTO> getAddressesById(java.util.UUID id);


    ServiceResponse<AddressResponseDTO> createAddresses(AddressRequestDTO addressRequestDTO);


    ServiceResponse<AddressResponseDTO> updateAddresses(java.util.UUID id, AddressRequestDTO addressRequestDTO);


    void deleteAddress(java.util.UUID id);
}
