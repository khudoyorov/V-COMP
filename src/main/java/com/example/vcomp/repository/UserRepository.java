package com.example.vcomp.repository;

import com.example.vcomp.model.UserModel;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    Boolean existsByEmailOrPhoneNumber(String email,String phoneNumber);

    Optional<UserModel> findFirstByPhoneNumber(String phoneNumber);
}
