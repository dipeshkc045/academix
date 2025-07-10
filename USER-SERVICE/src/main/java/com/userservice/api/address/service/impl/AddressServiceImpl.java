package com.userservice.api.address.service.impl;

import com.userservice.api.address.mapper.AddressRequestMapper;
import com.userservice.api.address.mapper.AddressResponseMapper;
import com.userservice.api.address.model.request.AddressRequestDTO;
import com.userservice.api.address.model.response.AddressResponseDTO;
import com.userservice.api.address.model.table.Address;
import com.userservice.api.address.repository.AddressRepository;
import com.userservice.api.address.service.AddressService;
import com.userservice.api.staff.model.response.StaffResponseDTO;
import com.userservice.common.ServiceResponse;
import com.userservice.common.Status;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressRequestMapper addressRequestMapper;
    private final AddressResponseMapper addressResponseMapper;

    @Override
    public ServiceResponse<List<AddressResponseDTO>> getAllAddresses() {
        List<AddressResponseDTO> addressResponseDTOList = addressRepository.findAll()
                                                                           .stream()
                                                                           .map(addressResponseMapper::toDto)
                                                                           .toList();

        return ServiceResponse.<List<AddressResponseDTO>>builder()
                              .status(Status.SUCCESS.name())
                              .message("Teacher list fetched successfully")
                              .data(addressResponseDTOList)
                              .code(200)
                              .build();
    }

    @Override
    public ServiceResponse<AddressResponseDTO> getAddressesById(java.util.UUID id) {
        AddressResponseDTO staffResponseDTO = addressRepository.findById(id)
                                                               .map(addressResponseMapper::toDto)
                                                               .orElseThrow(() -> new EntityNotFoundException(
                                                                   "Teacher not found with id: " + id));

        return ServiceResponse.<AddressResponseDTO>builder()
                              .status(Status.SUCCESS.name())
                              .message("Teacher fetched successfully")
                              .data(staffResponseDTO)
                              .code(200)
                              .build();
    }

    @Override
    public ServiceResponse<AddressResponseDTO> createAddresses(AddressRequestDTO staffRequestDTO) {
        Address address = addressRequestMapper.toEntity(staffRequestDTO);
        Address savedAddress = addressRepository.save(address);
        AddressResponseDTO staffResponseDTO = addressResponseMapper.toDto(savedAddress);

        return ServiceResponse.<AddressResponseDTO>builder()
                              .status(Status.SUCCESS.name())
                              .message("Teacher created successfully")
                              .data(staffResponseDTO)
                              .code(201)
                              .build();
    }

    @Override
    public ServiceResponse<AddressResponseDTO> updateAddresses(java.util.UUID id, AddressRequestDTO addressRequestDTO) {
        Address address = addressRepository.findById(id)
                                           .orElseThrow(
                                               () -> new EntityNotFoundException("Teacher not found with id: " + id));

        updateEntity(addressRequestDTO, address);
        Address addressSaved = addressRepository.save(address);
        AddressResponseDTO staffResponseDTO = addressResponseMapper.toDto(addressSaved);

        return ServiceResponse.<AddressResponseDTO>builder()
                              .status(Status.SUCCESS.name())
                              .message("Teacher updated successfully")
                              .data(staffResponseDTO)
                              .code(200)
                              .build();
    }

    @Override
    public void deleteAddress(java.util.UUID id) {
        if (!addressRepository.existsById(id)) {
            throw new EntityNotFoundException("Teacher not found with id: " + id);
        }

        addressRepository.deleteById(id);

        ServiceResponse.<Void>builder()
                       .status(Status.SUCCESS.name())
                       .message("Teacher deleted successfully")
                       .data(null)
                       .code(204)
                       .build();
    }


    public void updateEntity(AddressRequestDTO addressRequestDTO, Address address) {
        address.setAddressType(addressRequestDTO.getAddressType());

    }
}