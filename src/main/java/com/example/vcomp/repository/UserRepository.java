package com.example.vcomp.repository;

import com.example.vcomp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Boolean existsByEmailOrPhoneNumber(String email,String phoneNumber);

    Optional<Users> findFirstByPhoneNumber(String phoneNumber);
}
