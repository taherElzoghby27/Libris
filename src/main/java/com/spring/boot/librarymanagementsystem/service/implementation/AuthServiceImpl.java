package com.spring.boot.librarymanagementsystem.service.implementation;

import com.spring.boot.librarymanagementsystem.config.security.TokenHandler;
import com.spring.boot.librarymanagementsystem.dto.UserSystemDto;
import com.spring.boot.librarymanagementsystem.exception.custom_exception.InvalidCredentialsException;
import com.spring.boot.librarymanagementsystem.exception.custom_exception.NotFoundResourceException;
import com.spring.boot.librarymanagementsystem.mapper.UserMapper;
import com.spring.boot.librarymanagementsystem.service.AuthService;
import com.spring.boot.librarymanagementsystem.service.UserService;
import com.spring.boot.librarymanagementsystem.vm.user.UserResponseVm;
import com.spring.boot.librarymanagementsystem.vm.user.UserSystemLoginVm;
import com.spring.boot.librarymanagementsystem.vm.user.UserSystemSignUpVm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final TokenHandler tokenHandler;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseVm login(UserSystemLoginVm userSystemLoginVm) {
        UserSystemDto userSystemDto = userService.getUserByEmail(userSystemLoginVm.getEmail());
        if (!userSystemDto.getEnabled()) {
            throw new NotFoundResourceException("User not enabled");
        }
        boolean emailValid = userSystemDto.getEmail().equals(userSystemLoginVm.getEmail());
        boolean passwordValid = passwordEncoder.matches(userSystemLoginVm.getPassword(), userSystemDto.getPassword());
        if (emailValid && passwordValid) {
            UserResponseVm userResponseVm = UserMapper.INSTANCE.toUserResponseVm(userSystemDto);
            userResponseVm.setToken(tokenHandler.generateToken(userSystemDto));
            return userResponseVm;
        }
        throw new InvalidCredentialsException("Invalid credentials");
    }

    @Override
    public UserResponseVm signUp(UserSystemSignUpVm userSystemSignUpVm) {
        UserSystemDto userSystemDto = userService.createUserSystem(userSystemSignUpVm);
        //generate token
        UserResponseVm userResponseVm = UserMapper.INSTANCE.toUserResponseVm(userSystemDto);
        userResponseVm.setToken(tokenHandler.generateToken(userSystemDto));
        return userResponseVm;
    }
}
