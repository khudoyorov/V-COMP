package com.example.vcomp.service.mapper;

import com.example.vcomp.dto.UserDto;
import com.example.vcomp.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UserMapper implements CommonMapper<UserDto, Users> {
    @Autowired
    protected PasswordEncoder passwordEncoder;
    public abstract UserDto toDto(Users model);

    @Mapping(target = "password", expression = "java(passwordEncoder.encode(dto.getPassword()))")
    public abstract Users toModel(UserDto dto);
}
