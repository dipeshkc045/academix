package com.userservice.api.student.service;

import com.userservice.api.student.model.request.StudentRequestDTO;
import com.userservice.api.student.model.response.StudentResponseDTO;
import com.userservice.common.ServiceResponse;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    ServiceResponse<List<StudentResponseDTO>> getAllStudents();

    ServiceResponse<StudentResponseDTO> getStudentById(UUID id);

    ServiceResponse<StudentResponseDTO> createStudent(StudentRequestDTO student);

    ServiceResponse<StudentResponseDTO> updateStudent(UUID id, StudentRequestDTO studentRequestDTO);

    ServiceResponse<Void> deleteStudent(UUID id);
} 