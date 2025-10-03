package com.spring.boot.librarymanagementsystem.service;

import com.spring.boot.librarymanagementsystem.config.security.TokenHandler;
import com.spring.boot.librarymanagementsystem.dto.UserSystemDto;
import com.spring.boot.librarymanagementsystem.exception.custom_exception.InvalidCredentialsException;
import com.spring.boot.librarymanagementsystem.repository.UserRepo;
import com.spring.boot.librarymanagementsystem.service.implementation.AuthServiceImpl;
import com.spring.boot.librarymanagementsystem.service.implementation.UserServiceImpl;
import com.spring.boot.librarymanagementsystem.vm.user.UserResponseVm;
import com.spring.boot.librarymanagementsystem.vm.user.UserSystemLoginVm;
import com.spring.boot.librarymanagementsystem.vm.user.UserSystemSignUpVm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @Mock
    private UserRepo userRepo;
    @InjectMocks
    private AuthServiceImpl authService;
    @Mock
    private UserServiceImpl userService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private TokenHandler tokenHandler;

    @Test
    public void givenUser_whenLogin_thenSuccess() {
        String password = "taherTAHER152002";
        String encodedPassword = "gh87wgweweb8g";
        String generatedToken = "gh87wgweweeeeqwe1234245r34t5t34teteteeb8g";
        UserSystemDto userSystemDto = UserSystemDto.builder()
                .email("tataamen678@gmail.com")
                .fullName("taher amin")
                .username("taherElzoghby27")
                .enabled(true)
                .password(encodedPassword)
                .build();
        UserSystemLoginVm userSystemLoginVm = UserSystemLoginVm.builder()
                .email("tataamen678@gmail.com")
                .password(password).build();
        when(passwordEncoder.matches(userSystemLoginVm.getPassword(), userSystemDto.getPassword())).thenReturn(true);
        when(tokenHandler.generateToken(userSystemDto)).thenReturn(generatedToken);
        when(userService.getUserByEmail("tataamen678@gmail.com")).thenReturn(userSystemDto);
        UserResponseVm userFounded = authService.login(userSystemLoginVm);
        //assert
        assertAll("must equal",
                () -> assertEquals(userSystemDto.getEmail(), userFounded.getEmail()),
                () -> assertEquals(generatedToken, userFounded.getToken())
        );
    }

    @Test
    public void givenUser_whenLogin_thenFailed() {
        String password = "taherTAHER152002";
        String encodedPassword = "gh87wgweweb8g";
        UserSystemDto userSystemDto = UserSystemDto.builder()
                .email("tataamen678@gmail.com")
                .fullName("taher amin")
                .username("taherElzoghby27")
                .enabled(true)
                .password(encodedPassword)
                .build();
        UserSystemLoginVm userSystemLoginVm = UserSystemLoginVm.builder()
                .email("tataamen678@gmail.com")
                .password("taherTAHER").build();
        when(passwordEncoder.matches(userSystemLoginVm.getPassword(), userSystemDto.getPassword())).thenReturn(false);
        when(userService.getUserByEmail("tataamen678@gmail.com")).thenReturn(userSystemDto);
        assertThrows(InvalidCredentialsException.class, () -> authService.login(userSystemLoginVm));
    }

    @Test
    public void givenUser_whenSignUp_thenSuccess() {
        //arrange
        UserSystemSignUpVm userSystemSignUpVm = UserSystemSignUpVm.builder()
                .email("tataamen678@gmail.com")
                .fullName("taher amin")
                .username("taherElzoghby27")
                .password("taherTAHER152002")
                .build();
        UserSystemDto userSystemDto = UserSystemDto.builder()
                .email("tataamen678@gmail.com")
                .fullName("taher amin")
                .username("taherElzoghby27")
                .password("taherTAHER152002")
                .build();
        when(userService.createUserSystem(Mockito.any(UserSystemSignUpVm.class)))
                .thenReturn(userSystemDto);
        //act
        UserResponseVm usersResponseVm = authService.signUp(userSystemSignUpVm);
        //assert
        assertAll("must equal",
                () -> assertEquals(userSystemSignUpVm.getUsername(), usersResponseVm.getUsername()),
                () -> assertEquals(userSystemSignUpVm.getFullName(), usersResponseVm.getFullName()),
                () -> assertEquals(userSystemSignUpVm.getEmail(), usersResponseVm.getEmail())
        );
    }

}
