package com.spring.boot.librarymanagementsystem.mapper;

import com.spring.boot.librarymanagementsystem.dto.CategoryDto;
import com.spring.boot.librarymanagementsystem.entity.Category;
import com.spring.boot.librarymanagementsystem.vm.category.CategoryRequestVm;
import com.spring.boot.librarymanagementsystem.vm.category.CategoryResponseVm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "parent", target = "parent", ignore = true)
    Category toCategory(CategoryRequestVm categoryRequestVm);

    Category toCategory(CategoryDto categoryDto);

    CategoryDto toCategoryDto(Category category);

    CategoryResponseVm toCategoryResponseVm(Category category);
}
