package com.example.vcomp.service.mapper;

import com.example.vcomp.model.VariantValue;
import org.mapstruct.Mapper;
import com.example.vcomp.dto.VariantValueDto;

@Mapper(componentModel = "spring")
public interface VariantValueMapper extends CommonMapper<VariantValueDto, VariantValue> {
}