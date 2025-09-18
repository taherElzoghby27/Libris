package com.spring.boot.librarymanagementsystem.service;

import com.spring.boot.librarymanagementsystem.dto.UserSystemDto;
import com.spring.boot.librarymanagementsystem.vm.UsersResponseVm;

public interface UserService {
    UsersResponseVm getUsers(int page, int size);

    UserSystemDto getUserByEmail(String email);

    UserSystemDto getUserByUsername(String username);
}
