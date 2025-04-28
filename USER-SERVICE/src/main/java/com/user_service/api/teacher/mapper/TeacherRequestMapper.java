package com.user_service.api.teacher.mapper;

import com.user_service.api.teacher.model.request.TeacherRequestDTO;
import com.user_service.api.teacher.model.table.Teacher;
import com.user_service.common.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherRequestMapper extends GenericMapper<TeacherRequestDTO, Teacher> {
}
