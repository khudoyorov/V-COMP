package com.example.vcomp.service.impl;

import com.example.vcomp.dto.ResponseDto;
import com.example.vcomp.dto.UserDto;
import com.example.vcomp.model.UserModel;
import com.example.vcomp.repository.UserRepository;
import com.example.vcomp.service.UserService;
import com.example.vcomp.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<ResponseDto<UserDto>> addUser(UserDto dto) {

         if(userRepository.existsByEmailOrPhoneNumber(dto.getEmail(), dto.getPhoneNumber()))
             return ResponseEntity.badRequest().body(ResponseDto.<UserDto>builder().message("User with this phone number is already exists").build());
        UserModel savedUser = userRepository.save(userMapper.toModel(dto));
        return ResponseEntity.ok(
                ResponseDto.<UserDto>builder()
                        .message("OK")
                        .data(userMapper.toDto(savedUser))
                        .build()
        );
    }




    @Override
    public ResponseEntity<ResponseDto<UserDto>> editUser(UserDto dto) {
        Optional<UserModel> userModel = userRepository.findById(dto.getId());
        if (userModel.isEmpty()){
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
                        .data(userMapper.toDto(user))
                        .build()
        );
    }

    @Override
    public ResponseEntity<ResponseDto<?>> deleteUser(Integer id) {
        Optional<UserModel> userModel = userRepository.findById(id);
        if(userModel.isEmpty()){
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
                        .data(userMapper.toDto(userModel.get()))
                        .build()
        );
    }

    @Override
    public ResponseEntity<ResponseDto<List<UserDto>>> getAllUsers() {
        return ResponseEntity.ok(
                ResponseDto.<List<UserDto>>builder()
                        .message("OK")
                        .data(userRepository.findAll().stream().map(userMapper::toDto).toList())
                        .build()
        );
    }
}
