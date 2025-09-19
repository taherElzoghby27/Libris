package com.spring.boot.librarymanagementsystem.vm;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthorUpdateVm {
    @NotNull(message = "id must be not null")
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
}
