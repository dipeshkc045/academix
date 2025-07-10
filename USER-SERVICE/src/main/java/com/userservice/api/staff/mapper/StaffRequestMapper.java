package com.userservice.api.staff.mapper;

import com.userservice.api.staff.model.request.StaffRequestDTO;
import com.userservice.api.staff.model.table.Staff;
import com.userservice.common.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
@Service
public interface StaffRequestMapper extends GenericMapper<StaffRequestDTO, Staff> {
}
