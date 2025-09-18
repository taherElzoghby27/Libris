package com.spring.boot.librarymanagementsystem.service;

import com.spring.boot.librarymanagementsystem.vm.UserResponseVm;
import com.spring.boot.librarymanagementsystem.vm.UserSystemLoginVm;
import com.spring.boot.librarymanagementsystem.vm.UserSystemSignUpVm;

public interface AuthService {
    UserResponseVm login(UserSystemLoginVm userSystemLoginVm);

    UserResponseVm signUp(UserSystemSignUpVm userSystemSignUpVm);
}
