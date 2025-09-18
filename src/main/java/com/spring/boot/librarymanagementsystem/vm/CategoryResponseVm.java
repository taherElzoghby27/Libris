package com.spring.boot.librarymanagementsystem.vm;

import com.spring.boot.librarymanagementsystem.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryResponseVm {
    private String name;
    private CategoryDto parent;
    //books
}
