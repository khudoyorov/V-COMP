package com.example.vcomp.rest;

import com.example.vcomp.dto.ResponseDto;
import com.example.vcomp.dto.UserDto;
import com.example.vcomp.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserRepository {
    private final UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<ResponseDto<UserDto>> addUser(UserDto user) {
        return userService.addUser(user);
    }

    @PatchMapping
    public ResponseEntity<ResponseDto<UserDto>> editUser(UserDto user) {
        return userService.editUser(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDto<?>> deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<UserDto>>> getAllUsers() {
        return userService.getAllUsers();
    }

}
