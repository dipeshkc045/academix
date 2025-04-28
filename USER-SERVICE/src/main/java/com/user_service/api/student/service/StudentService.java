package com.user_service.api.student.service;

import com.user_service.api.student.model.request.StudentRequestDTO;
import com.user_service.api.student.model.response.StudentResponseDTO;
import com.user_service.api.student.model.table.Student;
import com.user_service.common.ServiceResponse;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    ServiceResponse<List<StudentResponseDTO>> getAllStudents();

    ServiceResponse<StudentResponseDTO> getStudentById(String id);

    ServiceResponse<StudentResponseDTO> createStudent(Student student);

    ServiceResponse<StudentResponseDTO> updateStudent(String id, StudentRequestDTO studentRequestDTO);

    ServiceResponse<Void> deleteStudent(String id);
} 