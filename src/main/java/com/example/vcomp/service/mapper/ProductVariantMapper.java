package com.example.vcomp.service.mapper;

import com.example.vcomp.dto.ProductVariantDto;
import com.example.vcomp.model.ProductVariant;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper extends CommonMapper<List<ProductVariantDto>, List<ProductVariant>> {
    ProductVariant toEntity(ProductVariantDto productVariantDto);
    ProductVariantDto toDto(ProductVariant productVariant);
}