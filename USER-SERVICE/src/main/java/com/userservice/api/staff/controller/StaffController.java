package com.userservice.api.staff.controller;

import com.userservice.api.staff.model.request.StaffRequestDTO;
import com.userservice.api.staff.model.response.StaffResponseDTO;
import com.userservice.api.staff.service.StaffService;
import com.userservice.common.ServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/staff")
public class StaffController {

    private final StaffService staffService;

    @GetMapping
    public ResponseEntity<ServiceResponse<List<StaffResponseDTO>>> getAllStaffs() {
        ServiceResponse<List<StaffResponseDTO>> response = staffService.getAllStaff();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse<StaffResponseDTO>> getStaffById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(staffService.getStaffById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse<StaffResponseDTO>> updateStaff(@PathVariable UUID id,
                                                                         @RequestBody StaffRequestDTO staffRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(staffService.updateStaff(id, staffRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceResponse<Void>> deleteStaff(@PathVariable UUID id) {
        staffService.deleteStaff(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ServiceResponse<>());
    }
}
