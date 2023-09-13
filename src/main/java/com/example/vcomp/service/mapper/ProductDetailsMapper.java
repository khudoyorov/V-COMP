package com.example.vcomp.service.mapper;

import com.example.vcomp.dto.ProductDetailsDto;
import com.example.vcomp.model.ProductDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDetailsMapper extends CommonMapper<ProductDetailsDto, ProductDetails>{
}
