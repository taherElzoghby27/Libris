package com.spring.boot.librarymanagementsystem.service.implementation;

import com.spring.boot.librarymanagementsystem.config.security.TokenHandler;
import com.spring.boot.librarymanagementsystem.dto.UserSystemDto;
import com.spring.boot.librarymanagementsystem.exception.InvalidCredentialsException;
import com.spring.boot.librarymanagementsystem.exception.NotFoundResourceException;
import com.spring.boot.librarymanagementsystem.mapper.UserMapper;
import com.spring.boot.librarymanagementsystem.service.AuthService;
import com.spring.boot.librarymanagementsystem.service.UserService;
import com.spring.boot.librarymanagementsystem.vm.UserResponseVm;
import com.spring.boot.librarymanagementsystem.vm.UserSystemLoginVm;
import com.spring.boot.librarymanagementsystem.vm.UserSystemSignUpVm;
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
        if (!userSystemDto.getEmail().equals(userSystemLoginVm.getEmail()) &&
            passwordEncoder.matches(userSystemLoginVm.getPassword(), userSystemDto.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
        UserResponseVm userResponseVm = UserMapper.INSTANCE.toUserResponseVm(userSystemDto);
        userResponseVm.setToken(tokenHandler.generateToken(userSystemDto));
        return userResponseVm;
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
