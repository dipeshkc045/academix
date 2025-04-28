package com.userservice.api.student.mapper;

import com.userservice.api.student.model.request.StudentRequestDTO;
import com.userservice.api.student.model.table.Student;
import com.userservice.common.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
@Service
public interface StudentRequestMapper extends GenericMapper<StudentRequestDTO, Student> {
}
