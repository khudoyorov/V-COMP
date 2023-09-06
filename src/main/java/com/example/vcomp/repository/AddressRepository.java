package com.example.vcomp.repository;

import com.example.vcomp.model.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Integer> {
    List<AddressModel> findAllByUserId(Integer id);
}
