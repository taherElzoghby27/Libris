package com.spring.boot.librarymanagementsystem.service.implementation;

import com.spring.boot.librarymanagementsystem.dto.CategoryDto;
import com.spring.boot.librarymanagementsystem.entity.Category;
import com.spring.boot.librarymanagementsystem.exception.custom_exception.BadRequestException;
import com.spring.boot.librarymanagementsystem.exception.custom_exception.NotFoundResourceException;
import com.spring.boot.librarymanagementsystem.mapper.CategoryMapper;
import com.spring.boot.librarymanagementsystem.repository.CategoryRepo;
import com.spring.boot.librarymanagementsystem.service.CategoryService;
import com.spring.boot.librarymanagementsystem.vm.CategoryRequestVm;
import com.spring.boot.librarymanagementsystem.vm.CategoryResponseVm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    @Override
    public CategoryDto createCategory(CategoryRequestVm categoryRequestVm) {
        if (Objects.nonNull(categoryRequestVm.getId())) {
            throw new BadRequestException("id must be empty");
        }
        Category category = CategoryMapper.INSTANCE.toCategory(categoryRequestVm);
        CategoryDto parentDto = null;
        if (Objects.nonNull(categoryRequestVm.getParent()) && !Objects.equals(categoryRequestVm.getParent(), category.getId())) {
            parentDto = getCategory(categoryRequestVm.getParent());
            Category parent = CategoryMapper.INSTANCE.toCategory(parentDto);
            category.setParent(parent);
        }
        category = categoryRepo.save(category);
        return CategoryMapper.INSTANCE.toCategoryDto(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryRequestVm categoryRequestVm) {
        boolean update = false;
        if (Objects.isNull(categoryRequestVm.getId())) {
            throw new BadRequestException("id must be not empty");
        }
        CategoryDto oldCategoryDto = getCategory(categoryRequestVm.getId());
        if (!oldCategoryDto.getName().equals(categoryRequestVm.getName())) {
            update = true;
            oldCategoryDto.setName(categoryRequestVm.getName());
        }
        if (Objects.nonNull(categoryRequestVm.getParent()) && !oldCategoryDto.getParent().getId().equals(categoryRequestVm.getParent())) {
            update = true;
            CategoryDto parent = getCategory(categoryRequestVm.getParent());
            oldCategoryDto.setParent(parent);
        }
        if (update) {
            Category category = CategoryMapper.INSTANCE.toCategory(oldCategoryDto);
            category = categoryRepo.save(category);
            return CategoryMapper.INSTANCE.toCategoryDto(category);
        }
        throw new BadRequestException("Data must be different");
    }

    @Transactional
    @Override
    public void deleteCategory(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("id must be not null");
        }
        getCategory(id);
        categoryRepo.deleteById(id);
    }

    @Override
    public CategoryDto getCategory(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("id must be not null");
        }
        Category category = categoryRepo.findById(id).orElseThrow(() -> new NotFoundResourceException("category not found"));
        return CategoryMapper.INSTANCE.toCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = categoryRepo.findAllCategoriesWithoutBooks();
        if (categoryList.isEmpty()) {
            throw new NotFoundResourceException("categories not found");
        }
        return categoryList.stream().map(CategoryMapper.INSTANCE::toCategoryDto).toList();
    }

    @Override
    public CategoryResponseVm getCategoryWithBooks(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("id must be not null");
        }
        Category category = categoryRepo.findCategoryWithBooks(id);
        if (Objects.isNull(category)) {
            throw new NotFoundResourceException("category not found");
        }
        return CategoryMapper.INSTANCE.toCategoryResponseVm(category);
    }
}

