package com.userservice.api.student.service.impl;

import com.userservice.api.student.mapper.StudentResponseMapper;
import com.userservice.api.student.model.request.StudentRequestDTO;
import com.userservice.api.student.model.response.StudentResponseDTO;
import com.userservice.api.student.model.table.Student;
import com.userservice.api.student.repository.StudentRepository;
import com.userservice.api.student.service.StudentService;
import com.userservice.common.ServiceResponse;
import com.userservice.common.Status;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentResponseMapper studentResponseMapper;


    @Override
    public ServiceResponse<List<StudentResponseDTO>> getAllStudents() {
        List<StudentResponseDTO> studentResponseDTOList = studentRepository.findAll()
                                                                           .stream()
                                                                           .map(studentResponseMapper::toDto)
                                                                           .toList();

        return ServiceResponse.<List<StudentResponseDTO>>builder()
                              .status(Status.SUCCESS.name())
                              .message("Student list fetched successfully")
                              .data(studentResponseDTOList)
                              .code(200)
                              .build();
    }


    @Override
    public ServiceResponse<StudentResponseDTO> getStudentById(String id) {
        StudentResponseDTO studentResponseDTO = studentRepository.findById(id)
                                                                 .map(studentResponseMapper::toDto)
                                                                 .orElseThrow(() -> new EntityNotFoundException(
                                                                     "Student not found with id: " + id));

        return ServiceResponse.<StudentResponseDTO>builder()
                              .status(Status.SUCCESS.name())
                              .message("Student fetched successfully")
                              .data(studentResponseDTO)
                              .code(200)
                              .build();
    }

    @Override
    public ServiceResponse<StudentResponseDTO> createStudent(Student student) {
        Student savedStudent = studentRepository.save(student);
        StudentResponseDTO studentResponseDTO = studentResponseMapper.toDto(savedStudent);

        return ServiceResponse.<StudentResponseDTO>builder()
                              .status(Status.SUCCESS.name())
                              .message("Student created successfully")
                              .data(studentResponseDTO)
                              .code(201)
                              .build();
    }

    @Override
    public ServiceResponse<StudentResponseDTO> updateStudent(String id, StudentRequestDTO studentRequestDTO) {

        Student existingStudent = studentRepository.findById(id)
                                                   .orElseThrow(() -> new EntityNotFoundException(
                                                       "Student not found with id: " + id));

        updateEntity(studentRequestDTO, existingStudent);
        Student savedStudent = studentRepository.save(existingStudent);
        StudentResponseDTO studentResponseDTO = studentResponseMapper.toDto(savedStudent);

        return ServiceResponse.<StudentResponseDTO>builder()
                              .status(Status.SUCCESS.name())
                              .message("Student updated successfully")
                              .data(studentResponseDTO)
                              .code(200)
                              .build();
    }


    @Override
    public ServiceResponse<Void> deleteStudent(String id) {

        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
        return ServiceResponse.<Void>builder()
                              .status(Status.SUCCESS.name())
                              .message("Student deleted successfully")
                              .data(null)
                              .code(204)
                              .build();
    }


    public void updateEntity(StudentRequestDTO studentRequestDTO, Student student) {
        student.setFirstName(studentRequestDTO.getFirstName());
    }

}