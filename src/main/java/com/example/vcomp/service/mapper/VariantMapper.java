package com.example.vcomp.service.mapper;

import com.example.vcomp.dto.VariantDto;
import com.example.vcomp.model.Variant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VariantMapper extends CommonMapper<VariantDto, Variant>{
}