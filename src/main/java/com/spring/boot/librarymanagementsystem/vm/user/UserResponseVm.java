package com.spring.boot.librarymanagementsystem.vm.user;

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
    private String fullName;
    private String email;
    private String token;
}
