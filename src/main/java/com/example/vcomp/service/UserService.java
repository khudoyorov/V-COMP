package com.example.vcomp.service;

import com.example.vcomp.dto.LoginDto;
import com.example.vcomp.dto.ResponseDto;
import com.example.vcomp.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface UserService {
    ResponseEntity<ResponseDto<String>> addUser(UserDto user);
    ResponseEntity<ResponseDto<UserDto>> editUser(UserDto user);
    ResponseEntity<ResponseDto<?>> deleteUser(Integer id);
    @ResponseStatus
    ResponseEntity<ResponseDto<List<UserDto>>> getAllUsers();

    ResponseEntity<ResponseDto<UserDto>> getUserByPhoneNumber(String phoneNumber);

    ResponseEntity<ResponseDto<UserDto>> getById(Integer id);

    ResponseEntity<ResponseDto<String>> loginUser(LoginDto loginDto);
}
