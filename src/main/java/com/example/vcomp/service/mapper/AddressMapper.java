package com.example.vcomp.service.mapper;

import com.example.vcomp.dto.AddressDto;
import com.example.vcomp.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper extends CommonMapper<AddressDto, Address>{
}
