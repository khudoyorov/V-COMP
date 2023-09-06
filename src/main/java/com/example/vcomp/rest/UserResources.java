package com.example.vcomp.rest;

import com.example.vcomp.dto.LoginDto;
import com.example.vcomp.dto.ResponseDto;
import com.example.vcomp.dto.UserDto;
import com.example.vcomp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserResources {
    private final UserService userService;

    @Operation(
            method = "Add new user",
            description = "Need to send UsersDto to this endpoint to create new user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Users info",
                    content = @Content(mediaType = "application/json"))
    )
    @PostMapping
    public ResponseEntity<ResponseDto<String>> addUser(UserDto user) {
        return userService.addUser(user);
    }

    @Operation(
            method = "Update user",
            description = "Need to send UsersDto to this endpoint to update user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Users info",
                    content = @Content(mediaType = "application/json"))
    )
    @PatchMapping
    public ResponseEntity<ResponseDto<UserDto>> editUser(UserDto user) {
        return userService.editUser(user);
    }

    @Operation(
            method = "Get user by id",
            description = "Need to send id to this endpoint to get user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Users info",
                    content = @Content(mediaType = "application/json"))
    )
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<UserDto>> getById(@PathVariable Integer id){
        return userService.getById(id);
    }

    @Operation(
            method = "Get user by phone number",
            description = "Need to send phone number to this endpoint to get user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Users info",
                    content = @Content(mediaType = "application/json"))
    )
    @GetMapping("/by-phone-number")
    public ResponseEntity<ResponseDto<UserDto>> getUserByPhoneNumber(@RequestParam String phoneNumber){
        return userService.getUserByPhoneNumber(phoneNumber);
    }

    @Operation(
            method = "Delete user by id",
            description = "Need to send id to this endpoint to delete user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Users info",
                    content = @Content(mediaType = "application/json"))
    )
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDto<?>> deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

    @Operation(
            method = "Get all user",
            description = "This endpoint return all users",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Users info",
                    content = @Content(mediaType = "application/json"))
    )
    @GetMapping
    public ResponseEntity<ResponseDto<List<UserDto>>> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(
            method = "Login user",
            description = "Need to send username and password to this endpoint to login user. You can get token.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Users info",
                    content = @Content(mediaType = "application/json"))
    )
    @PostMapping("/login")
    public ResponseEntity<ResponseDto<String>> loginUser(@RequestBody LoginDto loginDto) throws NoSuchMethodException {
        return userService.loginUser(loginDto);
    }

}
