package com.userservice.api.student.mapper;

import com.userservice.api.student.model.response.StudentResponseDTO;
import com.userservice.api.student.model.table.Student;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-28T17:16:33+0545",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 21.0.6 (Amazon.com Inc.)"
)
@Component
public class StudentResponseMapperImpl implements StudentResponseMapper {

    @Override
    public Student toEntity(StudentResponseDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Student student = new Student();

        return student;
    }

    @Override
    public StudentResponseDTO toDto(Student entity) {
        if ( entity == null ) {
            return null;
        }

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();

        return studentResponseDTO;
    }
}
