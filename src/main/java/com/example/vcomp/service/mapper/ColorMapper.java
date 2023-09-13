package com.example.vcomp.service.mapper;

import org.mapstruct.Mapper;
import com.example.vcomp.dto.ColorDto;
import com.example.vcomp.model.Color;

@Mapper(componentModel = "spring")
public interface ColorMapper extends CommonMapper<ColorDto, Color> {
}