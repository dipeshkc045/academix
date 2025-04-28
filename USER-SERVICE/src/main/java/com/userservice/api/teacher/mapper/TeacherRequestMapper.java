package com.userservice.api.teacher.mapper;

import com.userservice.api.teacher.model.request.TeacherRequestDTO;
import com.userservice.api.teacher.model.table.Teacher;
import com.userservice.common.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
@Service
public interface TeacherRequestMapper extends GenericMapper<TeacherRequestDTO, Teacher> {
}
