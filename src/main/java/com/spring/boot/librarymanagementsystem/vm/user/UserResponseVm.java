package com.spring.boot.librarymanagementsystem.vm.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserResponseVm {
    private Long id;
    private String username;
    @JsonProperty("full_name")
    private String fullName;
    private String email;
    private String token;
}
