package com.userservice.api.teacher.mapper;

import com.userservice.api.teacher.model.response.TeacherResponseDTO;
import com.userservice.api.teacher.model.table.Teacher;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-28T17:16:33+0545",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 21.0.6 (Amazon.com Inc.)"
)
@Component
public class TeacherResponseMapperImpl implements TeacherResponseMapper {

    @Override
    public Teacher toEntity(TeacherResponseDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Teacher teacher = new Teacher();

        return teacher;
    }

    @Override
    public TeacherResponseDTO toDto(Teacher entity) {
        if ( entity == null ) {
            return null;
        }

        TeacherResponseDTO teacherResponseDTO = new TeacherResponseDTO();

        return teacherResponseDTO;
    }
}
