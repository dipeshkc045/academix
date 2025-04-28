package com.userservice.api.teacher.service;

import com.userservice.api.teacher.model.request.TeacherRequestDTO;
import com.userservice.api.teacher.model.response.TeacherResponseDTO;
import com.userservice.common.ServiceResponse;

import java.util.List;

public interface TeacherService {


    ServiceResponse<List<TeacherResponseDTO>> getAllTeachers();


    ServiceResponse<TeacherResponseDTO> getTeacherById(String id);


    ServiceResponse<TeacherResponseDTO> createTeacher(TeacherRequestDTO teacherRequestDTO);


    ServiceResponse<TeacherResponseDTO> updateTeacher(String id, TeacherRequestDTO teacherRequestDTO);


    ServiceResponse<Void> deleteTeacher(String id);
}
