package com.user_service.api.teacher.mapper;

import com.user_service.api.teacher.model.response.TeacherResponseDTO;
import com.user_service.api.teacher.model.table.Teacher;
import com.user_service.common.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherResponseMapper extends GenericMapper<TeacherResponseDTO, Teacher> {
}
