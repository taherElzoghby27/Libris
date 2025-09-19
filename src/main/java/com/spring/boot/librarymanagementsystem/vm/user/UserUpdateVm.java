package com.spring.boot.librarymanagementsystem.vm.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserUpdateVm {
    @NotNull(message = "id must be not null")
    private Long id;
    private String username;
    private String password;
    @JsonProperty("full_name")
    private String fullName;
    @Email(message = "email format")
    private String email;
}
