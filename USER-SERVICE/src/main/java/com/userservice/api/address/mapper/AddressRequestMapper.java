package com.userservice.api.address.mapper;

import com.userservice.api.address.model.request.AddressRequestDTO;
import com.userservice.api.address.model.table.Address;
import com.userservice.common.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
@Service
public interface AddressRequestMapper extends GenericMapper<AddressRequestDTO, Address> {
}
