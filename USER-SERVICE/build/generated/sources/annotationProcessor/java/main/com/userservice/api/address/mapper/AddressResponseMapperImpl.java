package com.userservice.api.address.mapper;

import com.userservice.api.address.model.response.AddressResponseDTO;
import com.userservice.api.address.model.table.Address;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-10T12:22:10+0545",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 21.0.7 (Amazon.com Inc.)"
)
@Component
public class AddressResponseMapperImpl implements AddressResponseMapper {

    @Override
    public Address toEntity(AddressResponseDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Address address = new Address();

        return address;
    }

    @Override
    public AddressResponseDTO toDto(Address entity) {
        if ( entity == null ) {
            return null;
        }

        AddressResponseDTO addressResponseDTO = new AddressResponseDTO();

        return addressResponseDTO;
    }
}
