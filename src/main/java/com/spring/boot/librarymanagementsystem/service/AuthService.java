package com.spring.boot.librarymanagementsystem.service;

import com.spring.boot.librarymanagementsystem.vm.user.UserResponseVm;
import com.spring.boot.librarymanagementsystem.vm.user.UserSystemLoginVm;
import com.spring.boot.librarymanagementsystem.vm.user.UserSystemSignUpVm;

public interface AuthService {
    UserResponseVm login(UserSystemLoginVm userSystemLoginVm);

    UserResponseVm signUp(UserSystemSignUpVm userSystemSignUpVm);
}
