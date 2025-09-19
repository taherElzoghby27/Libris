package com.spring.boot.librarymanagementsystem.service.implementation;

import com.spring.boot.librarymanagementsystem.dto.CategoryDto;
import com.spring.boot.librarymanagementsystem.entity.Category;
import com.spring.boot.librarymanagementsystem.exception.custom_exception.BadRequestException;
import com.spring.boot.librarymanagementsystem.exception.custom_exception.NotFoundResourceException;
import com.spring.boot.librarymanagementsystem.mapper.CategoryMapper;
import com.spring.boot.librarymanagementsystem.repository.CategoryRepo;
import com.spring.boot.librarymanagementsystem.service.CategoryService;
import com.spring.boot.librarymanagementsystem.vm.category.CategoryRequestVm;
import com.spring.boot.librarymanagementsystem.vm.category.CategoryResponseVm;
import com.spring.boot.librarymanagementsystem.vm.category.CategoryUpdateVm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    @Transactional
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
    public CategoryDto updateCategory(CategoryUpdateVm categoryUpdateVm) {
        boolean update = false;
        CategoryDto oldCategoryDto = getCategory(categoryUpdateVm.getId());
        update = updateData(categoryUpdateVm, oldCategoryDto, update);
        if (update) {
            Category category = CategoryMapper.INSTANCE.toCategory(oldCategoryDto);
            category = categoryRepo.save(category);
            return CategoryMapper.INSTANCE.toCategoryDto(category);
        }
        throw new BadRequestException("Data must be different");
    }

    private boolean updateData(CategoryUpdateVm categoryUpdateVm, CategoryDto oldCategoryDto, boolean update) {
        if (categoryUpdateVm.getName() != null && !oldCategoryDto.getName().equals(categoryUpdateVm.getName())) {
            update = true;
            oldCategoryDto.setName(categoryUpdateVm.getName());
        }
        if (Objects.nonNull(categoryUpdateVm.getParent()) && (oldCategoryDto.getParent() == null) ||
            (Objects.nonNull(categoryUpdateVm.getParent()) &&
             !oldCategoryDto.getParent().getId().equals(categoryUpdateVm.getParent()))) {
            update = true;
            CategoryDto parent = getCategory(categoryUpdateVm.getParent());
            oldCategoryDto.setParent(parent);
        }
        return update;
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

