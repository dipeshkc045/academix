package com.userservice.api.teacher.service.impl;

import com.userservice.api.teacher.mapper.TeacherRequestMapper;
import com.userservice.api.teacher.mapper.TeacherResponseMapper;
import com.userservice.api.teacher.model.request.TeacherRequestDTO;
import com.userservice.api.teacher.model.response.TeacherResponseDTO;
import com.userservice.api.teacher.model.table.Teacher;
import com.userservice.api.teacher.repository.TeacherRepository;
import com.userservice.api.teacher.service.TeacherService;
import com.userservice.common.ServiceResponse;
import com.userservice.common.Status;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherRequestMapper teacherRequestMapper;
    private final TeacherResponseMapper teacherResponseMapper;

    @Override
    public ServiceResponse<List<TeacherResponseDTO>> getAllTeachers() {
        List<TeacherResponseDTO> teacherResponseDTOList = teacherRepository.findAll()
                                                                           .stream()
                                                                           .map(teacherResponseMapper::toDto)
                                                                           .toList();

        return ServiceResponse.<List<TeacherResponseDTO>>builder()
                              .status(Status.SUCCESS.name())
                              .message("Teacher list fetched successfully")
                              .data(teacherResponseDTOList)
                              .code(200)
                              .build();
    }

    @Override
    public ServiceResponse<TeacherResponseDTO> getTeacherById(String id) {
        TeacherResponseDTO teacherResponseDTO = teacherRepository.findById(id)
                                                                 .map(teacherResponseMapper::toDto)
                                                                 .orElseThrow(() -> new EntityNotFoundException(
                                                                     "Teacher not found with id: " + id));

        return ServiceResponse.<TeacherResponseDTO>builder()
                              .status(Status.SUCCESS.name())
                              .message("Teacher fetched successfully")
                              .data(teacherResponseDTO)
                              .code(200)
                              .build();
    }

    @Override
    public ServiceResponse<TeacherResponseDTO> createTeacher(TeacherRequestDTO teacherRequestDTO) {
        Teacher teacher = teacherRequestMapper.toEntity(teacherRequestDTO);
        Teacher savedTeacher = teacherRepository.save(teacher);
        TeacherResponseDTO teacherResponseDTO = teacherResponseMapper.toDto(savedTeacher);

        return ServiceResponse.<TeacherResponseDTO>builder()
                              .status(Status.SUCCESS.name())
                              .message("Teacher created successfully")
                              .data(teacherResponseDTO)
                              .code(201)
                              .build();
    }

    @Override
    public ServiceResponse<TeacherResponseDTO> updateTeacher(String id, TeacherRequestDTO teacherRequestDTO) {
        Teacher teacher = teacherRepository.findById(id)
                                           .orElseThrow(
                                               () -> new EntityNotFoundException("Teacher not found with id: " + id));

        updateEntity(teacherRequestDTO, teacher);
        Teacher savedTeacher = teacherRepository.save(teacher);
        TeacherResponseDTO teacherResponseDTO = teacherResponseMapper.toDto(savedTeacher);

        return ServiceResponse.<TeacherResponseDTO>builder()
                              .status(Status.SUCCESS.name())
                              .message("Teacher updated successfully")
                              .data(teacherResponseDTO)
                              .code(200)
                              .build();
    }

    @Override
    public ServiceResponse<Void> deleteTeacher(String id) {
        if (!teacherRepository.existsById(id)) {
            throw new EntityNotFoundException("Teacher not found with id: " + id);
        }

        teacherRepository.deleteById(id);

        return ServiceResponse.<Void>builder()
                              .status(Status.SUCCESS.name())
                              .message("Teacher deleted successfully")
                              .data(null)
                              .code(204)
                              .build();
    }


    public void updateEntity(TeacherRequestDTO teacherRequestDTO, Teacher teacher) {
        teacher.setFirstName(teacherRequestDTO.getFirstName());

    }
}