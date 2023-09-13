package com.example.vcomp.repository;

import com.example.vcomp.model.ImageQuality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageQualityRepository extends JpaRepository<ImageQuality, Integer> {
}
