package com.spring.boot.librarymanagementsystem.vm.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserSystemLoginVm {
    @Email(message = "email format")
    @NotEmpty(message = "email must be not empty")
    private String email;
    @NotEmpty(message = "password must be not empty")
    private String password;
}
