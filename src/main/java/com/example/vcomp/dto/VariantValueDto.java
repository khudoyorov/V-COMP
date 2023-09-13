package com.example.vcomp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VariantValueDto {
    private Integer valueId;
    private String value;
    private VariantDto variant;
    private List<ProductDetailsDto> productDetails;
}