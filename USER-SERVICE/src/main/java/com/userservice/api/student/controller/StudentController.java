package com.userservice.api.student.controller;

import com.userservice.api.student.model.request.StudentRequestDTO;
import com.userservice.api.student.model.response.StudentResponseDTO;
import com.userservice.api.student.model.table.Student;
import com.userservice.api.student.service.StudentService;
import com.userservice.common.ServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<ServiceResponse<StudentResponseDTO>> getStudentById(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentById(id));
    }


    @PostMapping
    public ResponseEntity<ServiceResponse<StudentResponseDTO>> createStudent(@RequestBody Student student) {
        ServiceResponse<StudentResponseDTO> response = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse<StudentResponseDTO>> updateStudent(@PathVariable("id") String id,
                                                                             @RequestBody StudentRequestDTO studentRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.updateStudent(id, studentRequestDTO));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceResponse<Void>> deleteStudent(@PathVariable("id") String id) {
        studentService.deleteStudent(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ServiceResponse<>());
    }
}
