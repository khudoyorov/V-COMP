package com.example.vcomp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Integer id;
    private String title;
    private String subTitle;
    private Double lat;
    private Double lon;
    private UserDto user;
}
