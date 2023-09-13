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
public class ProductDetailsDto {
    private Integer id;
    private ProductVariantDto productVariant;
    private VariantValueDto variantValue;
    private List<ImageDto> images;
}
