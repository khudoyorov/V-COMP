package com.example.vcomp.service.mapper;

import com.example.vcomp.dto.AddressDto;
import com.example.vcomp.model.AddressModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AddressMapper implements CommonMapper<AddressDto, AddressModel>{
    @Override
    public abstract AddressDto toDto(AddressModel addressModel);

    @Override
    public abstract AddressModel toModel(AddressDto addressDto);
}
