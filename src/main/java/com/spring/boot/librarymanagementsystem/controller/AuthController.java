package com.spring.boot.librarymanagementsystem.controller;

import com.spring.boot.librarymanagementsystem.dto.SuccessDto;
import com.spring.boot.librarymanagementsystem.dto.UserSystemDto;
import com.spring.boot.librarymanagementsystem.service.AuthService;
import com.spring.boot.librarymanagementsystem.service.UserService;
import com.spring.boot.librarymanagementsystem.vm.user.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

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

    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SuccessDto<ResponseEntity<UsersResponseVm>> getUsers(@RequestParam int page, @RequestParam int size) {
        return new SuccessDto<>(ResponseEntity.ok(userService.getUsers(page, size)));
    }

    @GetMapping("/user/{email}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SuccessDto<ResponseEntity<UserSystemDto>> getUserByEmail(@PathVariable String email) {
        return new SuccessDto<>(ResponseEntity.ok(userService.getUserByEmail(email)));
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SuccessDto<ResponseEntity<UserSystemDto>> getUserByUsername(@PathVariable String username) {
        return new SuccessDto<>(ResponseEntity.ok(userService.getUserByUsername(username)));
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SuccessDto<ResponseEntity<UserSystemDto>> getUserById(@PathVariable Long id) {
        return new SuccessDto<>(ResponseEntity.ok(userService.getUserById(id)));
    }

    @PatchMapping("/user")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SuccessDto<ResponseEntity<UserSystemDto>> updateUser(@Valid @RequestBody UserUpdateVm updateVm) {
        return new SuccessDto<>(ResponseEntity.ok(userService.updateUserSystem(updateVm)));
    }

    @DeleteMapping("/user")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SuccessDto<ResponseEntity<String>> deleteUser(@RequestParam Long id) {
        userService.deleteUserSystem(id);
        return new SuccessDto<>(ResponseEntity.ok("User deleted successfully"));
    }

    @PatchMapping("/roles")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SuccessDto<ResponseEntity<UserSystemDto>> updateUserRoles(@RequestParam("user_id") Long userId,
                                                                     @RequestParam("roles_name") List<String> roleNames) {
        return new SuccessDto<>(ResponseEntity.ok(userService.updateRolesForUserSystem(userId, roleNames)));
    }
}
