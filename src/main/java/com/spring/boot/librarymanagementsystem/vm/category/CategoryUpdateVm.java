package com.spring.boot.librarymanagementsystem.vm.category;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryUpdateVm {
    @NotNull(message = "id must be not null")
    private Long id;
    private String name;
    private Long parent;
}
