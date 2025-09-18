package com.spring.boot.librarymanagementsystem.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDto {
    private Long id;
    @NotEmpty(message = "username must be not empty")
    private String username;
    @NotEmpty(message = "password must be not empty")
    private String password;
    @NotEmpty(message = "full name must be not empty")
    private String fullName;
    @NotEmpty(message = "email must be not empty")
    private String email;
    private List<RoleDto> roles;
}
