package com.spring.boot.librarymanagementsystem.controller;

import com.spring.boot.librarymanagementsystem.dto.SuccessDto;
import com.spring.boot.librarymanagementsystem.service.AuthService;
import com.spring.boot.librarymanagementsystem.vm.user.UserResponseVm;
import com.spring.boot.librarymanagementsystem.vm.user.UserSystemLoginVm;
import com.spring.boot.librarymanagementsystem.vm.user.UserSystemSignUpVm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public SuccessDto<ResponseEntity<UserResponseVm>> login(@Valid @RequestBody UserSystemLoginVm loginVm) {
        UserResponseVm response = authService.login(loginVm);
        return new SuccessDto<>(ResponseEntity.ok(response));
    }

    @PostMapping("/sign-up")
    public SuccessDto<ResponseEntity<UserResponseVm>> signUp(@Valid @RequestBody UserSystemSignUpVm signUpVm) {
        UserResponseVm response = authService.signUp(signUpVm);
        return new SuccessDto<>(ResponseEntity.created(URI.create("sign-up")).body(response));
    }
}
