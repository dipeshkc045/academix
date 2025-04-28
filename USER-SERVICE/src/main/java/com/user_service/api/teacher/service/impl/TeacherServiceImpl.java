package com.user_service.api.teacher.service.impl;

import com.user_service.api.teacher.mapper.TeacherRequestMapper;
import com.user_service.api.teacher.mapper.TeacherResponseMapper;
import com.user_service.api.teacher.model.request.TeacherRequestDTO;
import com.user_service.api.teacher.model.response.TeacherResponseDTO;
import com.user_service.api.teacher.model.table.Teacher;
import com.user_service.api.teacher.repository.TeacherRepository;
import com.user_service.api.teacher.service.TeacherService;
import com.user_service.common.ServiceResponse;
import com.user_service.common.Status;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
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
                                                                           .collect(Collectors.toList());

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
                                                                 .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + id));

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