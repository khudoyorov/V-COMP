package com.example.vcomp.service.mapper;

import org.mapstruct.Mapper;
import com.example.vcomp.dto.CategoryDto;
import com.example.vcomp.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends CommonMapper<CategoryDto, Category> {
}