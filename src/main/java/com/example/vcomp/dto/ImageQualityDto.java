package com.example.vcomp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageQualityDto {
    private Integer id;
    private String pathLarge;
    private String pathMedium;
    private String pathSmall;
    private String ext;
    private String  quality;
    private ImageDto image;
    private LocalDateTime createdAt;
}
