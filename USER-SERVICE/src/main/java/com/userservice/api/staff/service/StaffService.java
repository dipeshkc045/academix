package com.userservice.api.staff.service;

import com.userservice.api.staff.model.request.StaffRequestDTO;
import com.userservice.api.staff.model.response.StaffResponseDTO;
import com.userservice.common.ServiceResponse;

import java.util.List;
import java.util.UUID;

public interface StaffService {

    ServiceResponse<List<StaffResponseDTO>> getAllStaff();

    ServiceResponse<StaffResponseDTO> getStaffById(UUID id);

    ServiceResponse<StaffResponseDTO> createStaff(StaffRequestDTO teacherRequestDTO);

    ServiceResponse<StaffResponseDTO> updateStaff(UUID id, StaffRequestDTO teacherRequestDTO);

    void deleteStaff(UUID id);
}
