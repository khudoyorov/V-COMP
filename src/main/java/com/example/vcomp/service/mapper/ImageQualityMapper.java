package com.example.vcomp.service.mapper;

import com.example.vcomp.dto.ImageQualityDto;
import com.example.vcomp.model.ImageQuality;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ImageQualityMapper extends CommonMapper<ImageQualityDto, ImageQuality>{
}