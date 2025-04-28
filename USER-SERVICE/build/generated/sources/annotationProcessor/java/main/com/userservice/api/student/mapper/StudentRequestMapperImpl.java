package com.userservice.api.student.mapper;

import com.userservice.api.student.model.request.StudentRequestDTO;
import com.userservice.api.student.model.table.Student;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-28T17:16:33+0545",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 21.0.6 (Amazon.com Inc.)"
)
@Component
public class StudentRequestMapperImpl implements StudentRequestMapper {

    @Override
    public Student toEntity(StudentRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Student student = new Student();

        return student;
    }

    @Override
    public StudentRequestDTO toDto(Student entity) {
        if ( entity == null ) {
            return null;
        }

        StudentRequestDTO studentRequestDTO = new StudentRequestDTO();

        return studentRequestDTO;
    }
}
