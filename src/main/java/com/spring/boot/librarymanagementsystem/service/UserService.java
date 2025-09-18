package com.spring.boot.librarymanagementsystem.service;

import com.spring.boot.librarymanagementsystem.dto.RoleDto;
import com.spring.boot.librarymanagementsystem.dto.UserSystemDto;
import com.spring.boot.librarymanagementsystem.vm.UserSystemRequestVm;
import com.spring.boot.librarymanagementsystem.vm.UsersResponseVm;

public interface UserService {
    UsersResponseVm getUsers(int page, int size);

    UserSystemDto getUserByEmail(String email);

    UserSystemDto getUserByUsername(String username);

    UserSystemDto getUserById(Long id);

    UserSystemDto createUserSystem(UserSystemRequestVm userSystemRequestVm);

    UserSystemDto updateUserSystem(UserSystemRequestVm userSystemRequestVm);

    void deleteUserSystem(Long id);

    UserSystemDto updateRolesForUserSystem(Long userId, RoleDto roleDto);
}
