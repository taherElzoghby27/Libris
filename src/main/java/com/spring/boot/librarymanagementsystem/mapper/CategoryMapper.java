package com.spring.boot.librarymanagementsystem.mapper;

import com.spring.boot.librarymanagementsystem.dto.CategoryDto;
import com.spring.boot.librarymanagementsystem.entity.Category;
import com.spring.boot.librarymanagementsystem.vm.CategoryRequestVm;
import com.spring.boot.librarymanagementsystem.vm.CategoryResponseVm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "parent", target = "parent", ignore = true)
    Category toCategory(CategoryRequestVm categoryRequestVm);

    Category toCategory(CategoryDto categoryDto);

    @Mapping(source = "parent", target = "parent", qualifiedByName = "categoryToId")
    CategoryDto toCategoryDto(Category category);

    CategoryResponseVm toCategoryResponseVm(Category category);

    @Named("categoryToId")
    default Long categoryToId(Category category) {
        return category.getId() == null ? null : category.getId();
    }
}
