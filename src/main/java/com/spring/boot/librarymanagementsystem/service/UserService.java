package com.spring.boot.librarymanagementsystem.service;
import com.spring.boot.librarymanagementsystem.dto.UserSystemDto;
import com.spring.boot.librarymanagementsystem.vm.user.UserSystemSignUpVm;
import com.spring.boot.librarymanagementsystem.vm.user.UserUpdateVm;
import com.spring.boot.librarymanagementsystem.vm.user.UsersResponseVm;

import java.util.List;

public interface UserService {
    UsersResponseVm getUsers(int page, int size);

    UserSystemDto getUserByEmail(String email);

    UserSystemDto getUserByUsername(String username);

    UserSystemDto getUserById(Long id);

    UserSystemDto createUserSystem(UserSystemSignUpVm userSystemSignUpVm);

    UserSystemDto updateUserSystem(UserUpdateVm userUpdateVm);

    void deleteUserSystem(Long id);

    UserSystemDto updateRolesForUserSystem(Long userId, List<String> roleName);
}
