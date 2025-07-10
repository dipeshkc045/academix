package com.userservice.api.teacher.service;

import com.userservice.api.teacher.model.request.TeacherRequestDTO;
import com.userservice.api.teacher.model.response.TeacherResponseDTO;
import com.userservice.common.ServiceResponse;

import java.util.List;
import java.util.UUID;

public interface TeacherService {


    ServiceResponse<List<TeacherResponseDTO>> getAllTeachers();


    ServiceResponse<TeacherResponseDTO> getTeacherById(UUID id);


    ServiceResponse<TeacherResponseDTO> createTeacher(TeacherRequestDTO teacherRequestDTO);


    ServiceResponse<TeacherResponseDTO> updateTeacher(UUID id, TeacherRequestDTO teacherRequestDTO);


    ServiceResponse<Void> deleteTeacher(UUID id);
}
