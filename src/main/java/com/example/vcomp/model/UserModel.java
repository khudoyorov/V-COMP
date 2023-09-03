package com.example.vcomp.model;

import com.example.vcomp.enums.UserGender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Users",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "phone_number"})}
)
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String email;
    String password;
    UserGender gender;
    LocalDate dateOfBirth;
    String phoneNumber;
}