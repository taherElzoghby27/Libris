package com.spring.boot.librarymanagementsystem.controller;

import com.spring.boot.librarymanagementsystem.dto.CategoryDto;
import com.spring.boot.librarymanagementsystem.dto.SuccessDto;
import com.spring.boot.librarymanagementsystem.service.CategoryService;
import com.spring.boot.librarymanagementsystem.vm.category.CategoryRequestVm;
import com.spring.boot.librarymanagementsystem.vm.category.CategoryResponseVm;
import com.spring.boot.librarymanagementsystem.vm.category.CategoryUpdateVm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SuccessDto<ResponseEntity<CategoryDto>> addCategory(@Valid @RequestBody CategoryRequestVm categoryRequestVm) {
        return new SuccessDto<>(
                ResponseEntity.created(URI.create("create-category")).
                        body(categoryService.createCategory(categoryRequestVm))
        );
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SuccessDto<ResponseEntity<CategoryDto>> updateCategory(@Valid @RequestBody CategoryUpdateVm categoryUpdateVm) {
        return new SuccessDto<>(
                ResponseEntity.ok(categoryService.updateCategory(categoryUpdateVm))
        );
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SuccessDto<ResponseEntity<String>> deleteCategory(@RequestParam Long id) {
        categoryService.deleteCategory(id);
        return new SuccessDto<>(
                ResponseEntity.ok("Category deleted successfully")
        );
    }

    @GetMapping("/{id}")
    public SuccessDto<ResponseEntity<CategoryDto>> getCategory(@PathVariable Long id) {
        return new SuccessDto<>(
                ResponseEntity.ok(categoryService.getCategory(id))
        );
    }

    @GetMapping
    public SuccessDto<ResponseEntity<List<CategoryDto>>> getCategories() {
        return new SuccessDto<>(
                ResponseEntity.ok(categoryService.getAllCategories())
        );
    }

    @GetMapping("/get-category-with-books/{id}")
    public SuccessDto<ResponseEntity<CategoryResponseVm>> getCategoryWithBooks(@PathVariable Long id) {
        return new SuccessDto<>(
                ResponseEntity.ok(categoryService.getCategoryWithBooks(id))
        );
    }
}
