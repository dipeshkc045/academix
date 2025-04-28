package com.user_service.api.teacher.service;

import com.user_service.api.teacher.model.request.TeacherRequestDTO;
import com.user_service.api.teacher.model.response.TeacherResponseDTO;
import com.user_service.api.teacher.model.table.Teacher;
import com.user_service.common.ServiceResponse;

import java.util.List;
import java.util.Optional;
public interface TeacherService {


    ServiceResponse<List<TeacherResponseDTO>> getAllTeachers();


    ServiceResponse<TeacherResponseDTO> getTeacherById(String id);


    ServiceResponse<TeacherResponseDTO> createTeacher(TeacherRequestDTO teacherRequestDTO);


    ServiceResponse<TeacherResponseDTO> updateTeacher(String id, TeacherRequestDTO teacherRequestDTO);


    ServiceResponse<Void> deleteTeacher(String id);
}
