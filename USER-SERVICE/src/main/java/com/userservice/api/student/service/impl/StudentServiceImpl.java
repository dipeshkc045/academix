package com.userservice.api.student.service.impl;

import com.userservice.api.student.mapper.StudentResponseMapper;
import com.userservice.api.student.model.request.StudentRequestDTO;
import com.userservice.api.student.model.response.StudentResponseDTO;
import com.userservice.api.student.model.table.Student;
import com.userservice.api.student.repository.StudentRepository;
import com.userservice.api.student.service.StudentService;
import com.userservice.api.user.model.table.User;
import com.userservice.api.user.repository.UserRepository;
import com.userservice.common.ServiceResponse;
import com.userservice.common.Status;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentResponseMapper studentResponseMapper;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

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
    public ServiceResponse<StudentResponseDTO> getStudentById(UUID id) {
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
    public ServiceResponse<StudentResponseDTO> createStudent(StudentRequestDTO studentRequestDTO) {
        // Step 1: Create and save User entity
        User user = User.builder()
                .username(studentRequestDTO.getEmail())
                .password("defaultPassword123")  // Or generate a random one and send to student
                .email(studentRequestDTO.getEmail())
                .firstName(studentRequestDTO.getFirstName())
                .lastName(studentRequestDTO.getLastName())
                .phoneNumber(studentRequestDTO.getPhoneNumber())
                .role(User.UserRole.STUDENT)
                .status(User.UserStatus.ACTIVE)
                .build();
        User savedUser = userRepository.save(user);

        // Step 2: Map to Student entity and set the saved User's ID
        Student student = modelMapper.map(studentRequestDTO, Student.class);
        student.setUserId(savedUser.getId());

        Student savedStudent = studentRepository.save(student);

        // Step 3: Map response
        StudentResponseDTO studentResponseDTO = modelMapper.map(savedStudent, StudentResponseDTO.class);

        return ServiceResponse.<StudentResponseDTO>builder()
                .status(Status.SUCCESS.name())
                .message("Student created successfully")
                .data(studentResponseDTO)
                .code(HttpStatus.CREATED.value())
                .build();
    }

    @Override
    public ServiceResponse<StudentResponseDTO> updateStudent(UUID id, StudentRequestDTO studentRequestDTO) {

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
    public ServiceResponse<Void> deleteStudent(UUID id) {

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
        if (studentRequestDTO.getAddress() != null) {
            student.setAddress(studentRequestDTO.getAddress());
        }
    }

}