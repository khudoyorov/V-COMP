package com.example.vcomp.service;

import com.example.vcomp.dto.ResponseDto;
import com.example.vcomp.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface UserService {
    ResponseEntity<ResponseDto<UserDto>> addUser(UserDto user);
    ResponseEntity<ResponseDto<UserDto>> editUser(UserDto user);
    ResponseEntity<ResponseDto<?>> deleteUser(Integer id);
    @ResponseStatus
    ResponseEntity<ResponseDto<List<UserDto>>> getAllUsers();
}
