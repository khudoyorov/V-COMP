package com.example.vcomp.service.mapper;

import com.example.vcomp.dto.ProductDto;
import com.example.vcomp.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring")
public abstract class ProductMapper implements CommonMapper<ProductDto, Product> {

    @Autowired
    protected CategoryMapper categoryMapper;
    @Autowired
    protected ProductVariantMapper productVariantMapper;

    @Mapping(target = "category", expression = "java(categoryMapper.toDto(product.getCategory()))")
    @Mapping(target = "productVariants", expression = "java(productVariantMapper.toDto(product.getProductVariants()))")
    public abstract ProductDto toDto(Product product);
    @Mapping(target = "category", expression = "java(categoryMapper.toEntity(product.getCategory()))")
    @Mapping(target = "productVariants", expression = "java(productVariantMapper.toEntity(product.getProductVariants()))")
    public abstract Product toEntity(ProductDto product);
}