package com.userservice.api.student.controller;

import com.userservice.api.student.model.request.StudentRequestDTO;
import com.userservice.api.student.model.response.StudentResponseDTO;
import com.userservice.api.student.service.StudentService;
import com.userservice.common.ServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<ServiceResponse<List<StudentResponseDTO>>> getAllStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse<StudentResponseDTO>> getStudentById(@PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse<StudentResponseDTO>> updateStudent(@PathVariable("id") UUID id,
                                                                             @RequestBody StudentRequestDTO studentRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.updateStudent(id, studentRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceResponse<Void>> deleteStudent(@PathVariable("id") UUID id) {
        studentService.deleteStudent(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ServiceResponse<>());
    }
}
