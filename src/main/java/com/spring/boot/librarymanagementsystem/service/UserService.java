package com.spring.boot.librarymanagementsystem.service;

import com.spring.boot.librarymanagementsystem.dto.RoleDto;
import com.spring.boot.librarymanagementsystem.dto.UserSystemDto;
import com.spring.boot.librarymanagementsystem.vm.user.UserSystemSignUpVm;
import com.spring.boot.librarymanagementsystem.vm.user.UsersResponseVm;

public interface UserService {
    UsersResponseVm getUsers(int page, int size);

    UserSystemDto getUserByEmail(String email);

    UserSystemDto getUserByUsername(String username);

    UserSystemDto getUserById(Long id);

    UserSystemDto createUserSystem(UserSystemSignUpVm userSystemSignUpVm);

    UserSystemDto updateUserSystem(UserSystemSignUpVm userSystemSignUpVm);

    void deleteUserSystem(Long id);

    UserSystemDto updateRolesForUserSystem(Long userId, RoleDto roleDto);
}
