package com.user_service.api.student.mapper;

import com.user_service.api.student.model.request.StudentRequestDTO;
import com.user_service.api.student.model.table.Student;
import com.user_service.common.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentRequestMapper extends GenericMapper<StudentRequestDTO, Student> {
}
