package com.spring.boot.librarymanagementsystem.vm;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserSystemRequestVm {
    private Long id;
    @NotEmpty(message = "username must be not empty")
    private String username;
    @NotEmpty(message = "password must be not empty")
    private String password;
    @NotEmpty(message = "full name must be not empty")
    private String fullName;
    @NotEmpty(message = "email must be not empty")
    private String email;
}
