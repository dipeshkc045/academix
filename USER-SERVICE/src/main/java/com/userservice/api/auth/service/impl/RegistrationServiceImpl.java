package com.userservice.api.auth.service.impl;

import com.userservice.api.auth.model.request.RegistrationRequestDTO;
import com.userservice.api.auth.service.RegistrationService;
import com.userservice.api.staff.model.request.StaffRequestDTO;
import com.userservice.api.staff.service.StaffService;
import com.userservice.api.student.model.request.StudentRequestDTO;
import com.userservice.api.student.service.StudentService;
import com.userservice.api.teacher.model.request.TeacherRequestDTO;
import com.userservice.api.teacher.service.TeacherService;
import com.userservice.common.ServiceResponse;
import com.userservice.common.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final StaffService staffService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ServiceResponse<Void> registerUser(RegistrationRequestDTO request) {


        switch (request.getUserType()) {
            case STUDENT -> {
                StudentRequestDTO studentRequest = StudentRequestDTO.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .phoneNumber(request.getPhoneNumber())
                    .address(request.getAddress())
                    .build();
                studentService.createStudent(studentRequest);
            }
            case TEACHER -> {
                TeacherRequestDTO teacherRequest = TeacherRequestDTO.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .phoneNumber(request.getPhoneNumber())
                    .address(request.getAddress())
                    .department(request.getDepartment())
                    .qualification(request.getQualification())
                    .build();
                teacherService.createTeacher(teacherRequest);
            }
            case STAFF -> {
                StaffRequestDTO staffRequest = StaffRequestDTO.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .phoneNumber(request.getPhoneNumber())
                    .address(request.getAddress())
                    .department(request.getDepartment())
                    .qualification(request.getQualification())
                    .build();
                staffService.createStaff(staffRequest);
            }
        }

        return ServiceResponse.<Void>builder()
            .status(Status.SUCCESS.name())
            .message("User registered successfully")
            .code(HttpStatus.CREATED.value())
            .build();
    }
} 