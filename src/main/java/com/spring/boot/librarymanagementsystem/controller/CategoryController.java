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
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("create-category")
    public SuccessDto<ResponseEntity<CategoryDto>> addCategory(@Valid @RequestBody CategoryRequestVm categoryRequestVm) {
        return new SuccessDto<>(
                ResponseEntity.created(URI.create("create-category")).
                        body(categoryService.createCategory(categoryRequestVm))
        );
    }

    @PutMapping("update-category")
    public SuccessDto<ResponseEntity<CategoryDto>> updateCategory(@Valid @RequestBody CategoryUpdateVm categoryUpdateVm) {
        return new SuccessDto<>(
                ResponseEntity.ok(categoryService.updateCategory(categoryUpdateVm))
        );
    }

    @DeleteMapping("delete-category")
    public SuccessDto<ResponseEntity<String>> deleteCategory(@RequestParam Long id) {
        categoryService.deleteCategory(id);
        return new SuccessDto<>(
                ResponseEntity.ok("Category deleted successfully")
        );
    }

    @GetMapping("get-category")
    public SuccessDto<ResponseEntity<CategoryDto>> getCategory(@RequestParam Long id) {
        return new SuccessDto<>(
                ResponseEntity.ok(categoryService.getCategory(id))
        );
    }

    @GetMapping("get-categories")
    public SuccessDto<ResponseEntity<List<CategoryDto>>> getCategories() {
        return new SuccessDto<>(
                ResponseEntity.ok(categoryService.getAllCategories())
        );
    }

    @GetMapping("get-category-with-books")
    public SuccessDto<ResponseEntity<CategoryResponseVm>> getCategoryWithBooks(@RequestParam Long id) {
        return new SuccessDto<>(
                ResponseEntity.ok(categoryService.getCategoryWithBooks(id))
        );
    }
}
