package com.userservice.api.student.service;

import com.userservice.api.student.model.request.StudentRequestDTO;
import com.userservice.api.student.model.response.StudentResponseDTO;
import com.userservice.api.student.model.table.Student;
import com.userservice.common.ServiceResponse;

import java.util.List;

public interface StudentService {
    ServiceResponse<List<StudentResponseDTO>> getAllStudents();

    ServiceResponse<StudentResponseDTO> getStudentById(String id);

    ServiceResponse<StudentResponseDTO> createStudent(Student student);

    ServiceResponse<StudentResponseDTO> updateStudent(String id, StudentRequestDTO studentRequestDTO);

    ServiceResponse<Void> deleteStudent(String id);
} 