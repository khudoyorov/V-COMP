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
    private Integer id;
    private String name;
    private String email;
    private String password;
    private UserGender gender;
    private LocalDate dateOfBirth;
    private String phoneNumber;
}
