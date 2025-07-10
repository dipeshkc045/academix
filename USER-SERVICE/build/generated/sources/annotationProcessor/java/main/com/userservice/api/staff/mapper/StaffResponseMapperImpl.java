package com.userservice.api.staff.mapper;

import com.userservice.api.staff.model.response.StaffResponseDTO;
import com.userservice.api.staff.model.table.Staff;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-10T12:22:10+0545",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 21.0.7 (Amazon.com Inc.)"
)
@Component
public class StaffResponseMapperImpl implements StaffResponseMapper {

    @Override
    public Staff toEntity(StaffResponseDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Staff staff = new Staff();

        return staff;
    }

    @Override
    public StaffResponseDTO toDto(Staff entity) {
        if ( entity == null ) {
            return null;
        }

        StaffResponseDTO staffResponseDTO = new StaffResponseDTO();

        return staffResponseDTO;
    }
}
