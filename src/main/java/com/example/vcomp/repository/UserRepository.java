package com.example.vcomp.repository;

import com.example.vcomp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    Boolean existsByEmailOrPhoneNumber(String email,String phoneNumber);
}
