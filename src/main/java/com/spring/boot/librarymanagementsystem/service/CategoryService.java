package com.spring.boot.librarymanagementsystem.service;

import com.spring.boot.librarymanagementsystem.dto.CategoryDto;
import com.spring.boot.librarymanagementsystem.vm.category.CategoryRequestVm;
import com.spring.boot.librarymanagementsystem.vm.category.CategoryResponseVm;
import com.spring.boot.librarymanagementsystem.vm.category.CategoryUpdateVm;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryRequestVm categoryRequestVm);

    CategoryDto updateCategory(CategoryUpdateVm categoryUpdateVm);

    void deleteCategory(Long id);

    CategoryDto getCategoryWithId(Long id);

    List<CategoryDto> getAllCategories();

    CategoryResponseVm getCategoryWithBooks(Long id);
}
