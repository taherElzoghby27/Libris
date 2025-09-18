package com.spring.boot.librarymanagementsystem.service;

import com.spring.boot.librarymanagementsystem.dto.CategoryDto;
import com.spring.boot.librarymanagementsystem.vm.CategoryRequestVm;
import com.spring.boot.librarymanagementsystem.vm.CategoryResponseVm;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryRequestVm categoryRequestVm);

    CategoryDto updateCategory(CategoryRequestVm categoryRequestVm);

    void deleteCategory(Long id);

    CategoryDto getCategory(Long id);

    List<CategoryDto> getAllCategories();

    CategoryResponseVm getCategoryWithBooks(Long id);
}
