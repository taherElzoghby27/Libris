package com.spring.boot.librarymanagementsystem.controller;

import com.spring.boot.librarymanagementsystem.dto.SuccessDto;
import com.spring.boot.librarymanagementsystem.dto.RoleDto;
import com.spring.boot.librarymanagementsystem.dto.UserSystemDto;
import com.spring.boot.librarymanagementsystem.service.AuthService;
import com.spring.boot.librarymanagementsystem.service.UserService;
import com.spring.boot.librarymanagementsystem.vm.user.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/get-users")
    public SuccessDto<ResponseEntity<UsersResponseVm>> getUsers(@RequestParam int page, @RequestParam int size) {
        return new SuccessDto<>(ResponseEntity.ok(userService.getUsers(page, size)));
    }

    @GetMapping("/get-user-by-email")
    public SuccessDto<ResponseEntity<UserSystemDto>> getUserByEmail(@RequestParam String email) {
        return new SuccessDto<>(ResponseEntity.ok(userService.getUserByEmail(email)));
    }

    @GetMapping("/get-user-by-username")
    public SuccessDto<ResponseEntity<UserSystemDto>> getUserByUsername(@RequestParam String username) {
        return new SuccessDto<>(ResponseEntity.ok(userService.getUserByUsername(username)));
    }

    @GetMapping("/get-user-by-id")
    public SuccessDto<ResponseEntity<UserSystemDto>> getUserById(@RequestParam Long id) {
        return new SuccessDto<>(ResponseEntity.ok(userService.getUserById(id)));
    }

    @PutMapping("/update-user")
    public SuccessDto<ResponseEntity<UserSystemDto>> updateUser(@Valid @RequestBody UserUpdateVm updateVm) {
        return new SuccessDto<>(ResponseEntity.ok(userService.updateUserSystem(updateVm)));
    }

    @DeleteMapping("/delete-user")
    public SuccessDto<ResponseEntity<String>> deleteUser(@RequestParam Long id) {
        userService.deleteUserSystem(id);
        return new SuccessDto<>(ResponseEntity.ok("User deleted successfully"));
    }

    @PutMapping("/update-user-roles")
    public SuccessDto<ResponseEntity<UserSystemDto>> updateUserRoles(@RequestParam("user_id") Long userId,
                                                                     @RequestParam("roles_name") List<String> roleNames) {
        return new SuccessDto<>(ResponseEntity.ok(userService.updateRolesForUserSystem(userId, roleNames)));
    }
}
