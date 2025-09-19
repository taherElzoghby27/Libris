package com.spring.boot.librarymanagementsystem.vm.category;

import com.spring.boot.librarymanagementsystem.dto.BookDto;
import com.spring.boot.librarymanagementsystem.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryResponseVm {
    private String name;
    private CategoryDto parent;
    private List<BookDto> books = new ArrayList<>();
}
