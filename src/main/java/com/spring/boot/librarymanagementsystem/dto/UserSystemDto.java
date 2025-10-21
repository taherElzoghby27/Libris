package com.spring.boot.librarymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserSystemDto {
    private Long id;
    @NotEmpty(message = "username must be not empty")
    private String username;
    @NotEmpty(message = "password must be not empty")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotEmpty(message = "full name must be not empty")
    @JsonProperty("full_name")
    private String fullName;
    @Email(message = "email format")
    @NotEmpty(message = "email must be not empty")
    private String email;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean enabled = true;
    private List<RoleDto> roles;
}
