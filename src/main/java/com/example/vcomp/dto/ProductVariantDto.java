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
public class ProductVariantDto {
    private Integer id;
    private Double price;
    private String sku;
    //private ProductDto product;
    private List<VariantValueDto> variantValueIds;
}