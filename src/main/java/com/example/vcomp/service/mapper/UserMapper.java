package com.example.vcomp.service.mapper;

import com.example.vcomp.dto.UserDto;
import com.example.vcomp.model.UserModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper{
    UserDto toDto(UserModel model);
    UserModel toModel(UserDto dto);
}
