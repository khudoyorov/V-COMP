package com.example.vcomp.service.impl;

import com.example.vcomp.configuration.JwtService;
import com.example.vcomp.dto.LoginDto;
import com.example.vcomp.dto.ResponseDto;
import com.example.vcomp.dto.UserDto;
import com.example.vcomp.model.UserModel;
import com.example.vcomp.repository.UserRepository;
import com.example.vcomp.service.UserService;
import com.example.vcomp.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;

    @Override
    public ResponseEntity<ResponseDto<String>> addUser(UserDto dto) {
        try {
            if (userRepository.existsByEmailOrPhoneNumber(dto.getEmail(), dto.getPhoneNumber()))
                return ResponseEntity.badRequest().body(ResponseDto.<String>builder().message("User with this phone number is already exists").build());
            UserModel savedUser = userRepository.save(userMapper.toModel(dto));
            return ResponseEntity.ok(
                    ResponseDto.<String>builder()
                            .message("OK")
                            .success(true)
                            .data(jwtService.generateToken(savedUser))
                            .build()
            );
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(
                    ResponseDto.<String>builder()
                            .message("Database error : " + e.getMessage())
                            .build()
            );
        }
    }

    @Override
    public ResponseEntity<ResponseDto<UserDto>> editUser(UserDto dto) {
        if (dto.getId() == null){
            return ResponseEntity.badRequest().body(
                    ResponseDto.<UserDto>builder()
                            .message("Id is null")
                            .build()
            );
        }
        try {
            Optional<UserModel> userModel = userRepository.findById(dto.getId());

            if (userModel.isEmpty()) {
                return ResponseEntity.badRequest().body(
                        ResponseDto.<UserDto>builder()
                                .message("User not found")
                                .build()
                );
            }
            UserModel user = userModel.get();
            if (!user.getName().equals(dto.getName()))
                user.setName(dto.getName());
            if (!user.getPassword().equals(dto.getPassword()))
                user.setPassword(dto.getPassword());
            if (!user.getEmail().equals(dto.getEmail()))
                user.setEmail(dto.getEmail());
            if (!user.getDateOfBirth().equals(dto.getDateOfBirth()))
                user.setDateOfBirth(dto.getDateOfBirth());

            userRepository.save(user);

            return ResponseEntity.ok(
                    ResponseDto.<UserDto>builder()
                            .message("OK")
                            .success(true)
                            .data(userMapper.toDto(user))
                            .build()
            );
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(
                    ResponseDto.<UserDto>builder()
                            .message("Database error : " + e.getMessage())
                            .build()
            );
        }
    }

    @Override
    public ResponseEntity<ResponseDto<?>> deleteUser(Integer id) {
        if (id == null){
            return ResponseEntity.badRequest().body(
                    ResponseDto.<UserDto>builder()
                            .message("Id is null")
                            .build()
            );
        }

        try {
            Optional<UserModel> userModel = userRepository.findById(id);

            if (userModel.isEmpty()) {
                return ResponseEntity.badRequest().body(
                        ResponseDto.<UserDto>builder()
                                .message("User not found")
                                .build()
                );
            }
            userRepository.deleteById(id);
            return ResponseEntity.ok(
                    ResponseDto.<UserDto>builder()
                            .message("OK")
                            .success(true)
                            .data(userMapper.toDto(userModel.get()))
                            .build()
            );
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(
                    ResponseDto.<UserDto>builder()
                            .message("Database error : " + e.getMessage())
                            .build()
            );
        }
    }

    @Override
    public ResponseEntity<ResponseDto<List<UserDto>>> getAllUsers() {
        return ResponseEntity.ok(
                ResponseDto.<List<UserDto>>builder()
                        .message("OK")
                        .success(true)
                        .data(userRepository.findAll().stream().map(userMapper::toDto).toList())
                        .build()
        );
    }

    @Override
    public ResponseEntity<ResponseDto<UserDto>> getUserByPhoneNumber(String phoneNumber) {
        try {
            Optional<UserModel> userModel = userRepository.findFirstByPhoneNumber(phoneNumber);
            return userModel.map(model -> ResponseEntity.ok().body(
                    ResponseDto.<UserDto>builder()
                            .message("OK")
                            .success(true)
                            .data(userMapper.toDto(model))
                            .build()
            )).orElseGet(() -> ResponseEntity.badRequest().body(
                    ResponseDto.<UserDto>builder()
                            .message("User not found")
                            .build()
            ));

        }catch (Exception e){
            return ResponseEntity.internalServerError().body(
                    ResponseDto.<UserDto>builder()
                            .message("Database error : " + e.getMessage())
                            .build()
            );
        }
    }

    @Override
    public ResponseEntity<ResponseDto<UserDto>> getById(Integer id) {
        if (id == null){
            return ResponseEntity.badRequest().body(
                    ResponseDto.<UserDto>builder()
                            .message("Id is null")
                            .build()
            );
        }
        try {
            Optional<UserModel> userModel = userRepository.findById(id);

            return userModel.map(model -> ResponseEntity.ok(
                    ResponseDto.<UserDto>builder()
                            .message("OK")
                            .success(true)
                            .data(userMapper.toDto(model))
                            .build()
            )).orElseGet(() -> ResponseEntity.badRequest().body(
                    ResponseDto.<UserDto>builder()
                            .message("User not found")
                            .build()
            ));
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(
                    ResponseDto.<UserDto>builder()
                            .message("Database error : " + e.getMessage())
                            .build()
            );
        }
    }

    @Override
    public ResponseEntity<ResponseDto<String>> loginUser(LoginDto loginDto) {
        UserModel user = loadUserByUsername(loginDto.getUsername());
        if (!encoder.matches(loginDto.getPassword(),user.getPassword())){
            return ResponseEntity.badRequest().body(
                    ResponseDto.<String>builder()
                            .message("Password is not correct "+user.getPassword())
                            .build()
            );
        }

        return ResponseEntity.ok().body(
                ResponseDto.<String>builder()
                        .success(true)
                        .message("OK")
                        .data(jwtService.generateToken(user))
                        .build()
        );
    }

    private UserModel loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> users = userRepository.findFirstByPhoneNumber(username);
        if (users.isEmpty()) throw new UsernameNotFoundException("User with phone number: " + username + " is not found");
        return users.get();
    }
}
