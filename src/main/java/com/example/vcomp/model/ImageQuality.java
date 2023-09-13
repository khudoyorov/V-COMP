package com.example.vcomp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageQuality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String pathLarge;
    private String pathMedium;
    private String pathSmall;
    private String ext;
    private String quality;
    @ManyToOne(fetch = FetchType.LAZY)
    private Image image;
    @CreatedDate
    @CreationTimestamp
    private LocalDateTime createdAt;
}
