package com.example.vcomp.service.mapper;

import com.example.vcomp.dto.ImageDto;
import com.example.vcomp.model.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper extends CommonMapper<ImageDto, Image>{
}
