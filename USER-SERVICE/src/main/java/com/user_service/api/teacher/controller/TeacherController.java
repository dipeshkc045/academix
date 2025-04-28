package com.user_service.api.teacher.controller;

import com.user_service.api.teacher.model.request.TeacherRequestDTO;
import com.user_service.api.teacher.model.response.TeacherResponseDTO;
import com.user_service.api.teacher.service.TeacherService;
import com.user_service.common.ServiceResponse;
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
@RequiredArgsConstructor
@RequestMapping("/api/v1/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<ServiceResponse<List<TeacherResponseDTO>>> getAllTeachers() {
        ServiceResponse<List<TeacherResponseDTO>> response = teacherService.getAllTeachers();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse<TeacherResponseDTO>> getTeacherById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.getTeacherById(id));
    }

    @PostMapping
    public ResponseEntity<ServiceResponse<TeacherResponseDTO>> createTeacher(
        @RequestBody TeacherRequestDTO teacherDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createTeacher(teacherDTO));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse<TeacherResponseDTO>> updateTeacher(@PathVariable String id,
                                                                             @RequestBody TeacherRequestDTO teacherDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.updateTeacher(id, teacherDTO));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceResponse<Void>> deleteTeacher(@PathVariable String id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ServiceResponse<>());
    }
}
