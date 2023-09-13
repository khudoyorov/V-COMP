package com.example.vcomp.service.mapper;

import com.example.vcomp.dto.BrandDto;
import com.example.vcomp.model.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper extends CommonMapper<BrandDto, Brand>{
}
