package com.spring.boot.librarymanagementsystem.vm.category;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryRequestVm {
    private Long id;
    @NotEmpty(message = "name must be not empty")
    private String name;
    private Long parent;
}
