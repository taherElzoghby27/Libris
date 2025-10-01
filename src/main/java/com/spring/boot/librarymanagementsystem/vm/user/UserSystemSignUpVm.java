package com.spring.boot.librarymanagementsystem.vm.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserSystemSignUpVm {
    private Long id;
    @NotEmpty(message = "username must be not empty")
    private String username;
    @NotEmpty(message = "password must be not empty")
    private String password;
    @NotEmpty(message = "full name must be not empty")
    @JsonProperty("full_name")
    private String fullName;
    @Email(message = "email format")
    @NotEmpty(message = "email must be not empty")
    private String email;
}
