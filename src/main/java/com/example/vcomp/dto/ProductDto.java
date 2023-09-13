package com.example.vcomp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Integer id;
    @NotBlank
    private String name;
    private Integer amount;
    private Integer price;
    @NotBlank
    private String description;
    private BrandDto brand;
    private CategoryDto category;
    private List<ProductVariantDto> productVariants;
    private Boolean isAvailable;
}