package com.spring.boot.librarymanagementsystem.service;

import com.spring.boot.librarymanagementsystem.dto.UserDto;
import com.spring.boot.librarymanagementsystem.vm.UsersResponseVm;

public interface UserService {
    UsersResponseVm getUsers();

    UserDto getUserByEmail(String email);

    UserDto getUserByUsername(String username);
}
