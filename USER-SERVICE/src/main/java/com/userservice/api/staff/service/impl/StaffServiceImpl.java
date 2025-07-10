package com.userservice.api.staff.service.impl;

import com.userservice.api.staff.mapper.StaffRequestMapper;
import com.userservice.api.staff.mapper.StaffResponseMapper;
import com.userservice.api.staff.model.request.StaffRequestDTO;
import com.userservice.api.staff.model.response.StaffResponseDTO;
import com.userservice.api.staff.model.table.Staff;
import com.userservice.api.staff.repository.StaffRepository;
import com.userservice.api.staff.service.StaffService;
import com.userservice.common.ServiceResponse;
import com.userservice.common.Status;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final StaffRequestMapper staffRequestMapper;
    private final StaffResponseMapper staffResponseMapper;

    @Override
    public ServiceResponse<List<StaffResponseDTO>> getAllStaff() {
        List<StaffResponseDTO> staffResponseDTOList = staffRepository.findAll()
                                                                     .stream()
                                                                     .map(staffResponseMapper::toDto)
                                                                     .toList();

        return ServiceResponse.<List<StaffResponseDTO>>builder()
                              .status(Status.SUCCESS.name())
                              .message("Teacher list fetched successfully")
                              .data(staffResponseDTOList)
                              .code(200)
                              .build();
    }

    @Override
    public ServiceResponse<StaffResponseDTO> getStaffById(UUID id) {
        StaffResponseDTO staffResponseDTO = staffRepository.findById(id)
                                                           .map(staffResponseMapper::toDto)
                                                           .orElseThrow(() -> new EntityNotFoundException(
                                                               "Teacher not found with id: " + id));

        return ServiceResponse.<StaffResponseDTO>builder()
                              .status(Status.SUCCESS.name())
                              .message("Teacher fetched successfully")
                              .data(staffResponseDTO)
                              .code(200)
                              .build();
    }

    @Override
    public ServiceResponse<StaffResponseDTO> createStaff(StaffRequestDTO staffRequestDTO) {
        Staff staff = staffRequestMapper.toEntity(staffRequestDTO);
        Staff savedStaff = staffRepository.save(staff);
        StaffResponseDTO staffResponseDTO = staffResponseMapper.toDto(savedStaff);

        return ServiceResponse.<StaffResponseDTO>builder()
                              .status(Status.SUCCESS.name())
                              .message("Teacher created successfully")
                              .data(staffResponseDTO)
                              .code(201)
                              .build();
    }

    @Override
    public ServiceResponse<StaffResponseDTO> updateStaff(UUID id, StaffRequestDTO staffRequestDTO) {
        Staff staff = staffRepository.findById(id)
                                     .orElseThrow(
                                         () -> new EntityNotFoundException("Teacher not found with id: " + id));

        updateEntity(staffRequestDTO, staff);
        Staff savedStaff = staffRepository.save(staff);
        StaffResponseDTO staffResponseDTO = staffResponseMapper.toDto(savedStaff);

        return ServiceResponse.<StaffResponseDTO>builder()
                              .status(Status.SUCCESS.name())
                              .message("Teacher updated successfully")
                              .data(staffResponseDTO)
                              .code(200)
                              .build();
    }

    @Override
    public void deleteStaff(UUID id) {
        if (!staffRepository.existsById(id)) {
            throw new EntityNotFoundException("Teacher not found with id: " + id);
        }

        staffRepository.deleteById(id);

        ServiceResponse.<Void>builder()
                       .status(Status.SUCCESS.name())
                       .message("Teacher deleted successfully")
                       .data(null)
                       .code(204)
                       .build();
    }

    public void updateEntity(StaffRequestDTO staffRequestDTO, Staff staff) {
        if (staffRequestDTO.getDepartment() != null) {
            staff.setDepartment(staffRequestDTO.getDepartment());
        }
        if (staffRequestDTO.getQualification() != null) {
            staff.setQualification(staffRequestDTO.getQualification());
        }
    }
}