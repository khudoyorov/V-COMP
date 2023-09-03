package com.example.vcomp.dto;

import com.example.vcomp.enums.UserGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    Integer id;
    String name;
    String email;
    String password;
    UserGender gender;
    LocalDate dateOfBirth;
    String phoneNumber;
}
