package com.spring.boot.librarymanagementsystem.service.implementation;

import com.spring.boot.librarymanagementsystem.dto.RoleDto;
import com.spring.boot.librarymanagementsystem.dto.UserSystemDto;
import com.spring.boot.librarymanagementsystem.entity.Role;
import com.spring.boot.librarymanagementsystem.entity.UserSystem;
import com.spring.boot.librarymanagementsystem.exception.custom_exception.BadRequestException;
import com.spring.boot.librarymanagementsystem.exception.custom_exception.NotFoundResourceException;
import com.spring.boot.librarymanagementsystem.mapper.RoleMapper;
import com.spring.boot.librarymanagementsystem.mapper.UserMapper;
import com.spring.boot.librarymanagementsystem.repository.UserRepo;
import com.spring.boot.librarymanagementsystem.service.PaginationService;
import com.spring.boot.librarymanagementsystem.service.RoleService;
import com.spring.boot.librarymanagementsystem.service.UserService;
import com.spring.boot.librarymanagementsystem.utils.enums.RoleType;
import com.spring.boot.librarymanagementsystem.vm.user.UserSystemSignUpVm;
import com.spring.boot.librarymanagementsystem.vm.user.UserUpdateVm;
import com.spring.boot.librarymanagementsystem.vm.user.UsersResponseVm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Override
    public UsersResponseVm getUsers(int page, int size) {
        Pageable pageable = PaginationService.getPageable(page, size);
        Page<UserSystem> result = userRepo.findAll(pageable);
        if (result.isEmpty()) {
            throw new NotFoundResourceException("users not found");
        }
        return new UsersResponseVm(
                result.map(UserMapper.INSTANCE::toUserDto).getContent(),
                result.getTotalElements()
        );
    }

    @Override
    public UserSystemDto getUserByEmail(String email) {
        if (Objects.isNull(email)) {
            throw new BadRequestException("email must be not null");
        }
        UserSystem userSystem = userRepo.findByEmail(email);
        if (Objects.isNull(userSystem)) {
            throw new NotFoundResourceException("user not found");
        }
        return UserMapper.INSTANCE.toUserDto(userSystem);
    }

    @Override
    public UserSystemDto getUserByUsername(String username) {
        if (Objects.isNull(username)) {
            throw new BadRequestException("username must be not null");
        }
        UserSystem userSystem = userRepo.findByUsername(username);
        if (Objects.isNull(userSystem)) {
            throw new NotFoundResourceException("user not found");
        }
        return UserMapper.INSTANCE.toUserDto(userSystem);
    }

    @Override
    public UserSystemDto getUserById(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("id must be not null");
        }
        Optional<UserSystem> result = userRepo.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundResourceException("user not found");
        }
        return UserMapper.INSTANCE.toUserDto(result.get());
    }

    @Transactional
    @Override
    public UserSystemDto createUserSystem(UserSystemSignUpVm userSystemSignUpVm) {
        if (Objects.nonNull(userSystemSignUpVm.getId())) {
            throw new BadRequestException("id must be null");
        }
        UserSystem userSystem = UserMapper.INSTANCE.toUserSystem(userSystemSignUpVm);
        userSystem.setPassword(passwordEncoder.encode(userSystem.getPassword()));
        //check role if exist
        Role role = getDefaultRole();
        userSystem.setRoles(List.of(role));
        userSystem = userRepo.save(userSystem);
        return UserMapper.INSTANCE.toUserDto(userSystem);
    }

    private Role getDefaultRole() {
        RoleDto roleDto = roleService.getRoleByName(RoleType.STAFF.toString());
        return RoleMapper.INSTANCE.toRole(roleDto);
    }

    @Override
    public UserSystemDto updateUserSystem(UserUpdateVm userUpdateVm) {
        boolean update = false;
        UserSystemDto userSystemDto = getUserById(userUpdateVm.getId());
        update = updateData(userUpdateVm, userSystemDto, update);
        if (!update) {
            throw new BadRequestException("data must be different");
        }
        UserSystem userSystem = UserMapper.INSTANCE.toUserSystem(userSystemDto);
        userSystem.setPassword(passwordEncoder.encode(userSystem.getPassword()));
        userSystem = userRepo.save(userSystem);
        return UserMapper.INSTANCE.toUserDto(userSystem);
    }

    private static boolean updateData(UserUpdateVm userUpdateVm, UserSystemDto userSystemDto, boolean update) {
        if (userUpdateVm.getUsername() != null && !userSystemDto.getUsername().equals(userUpdateVm.getUsername())) {
            update = true;
            userSystemDto.setUsername(userUpdateVm.getUsername());
        }
        if (userUpdateVm.getPassword() != null && !userSystemDto.getPassword().equals(userUpdateVm.getPassword())) {
            update = true;
            userSystemDto.setPassword(userUpdateVm.getPassword());
        }
        if (userUpdateVm.getEmail() != null && !userSystemDto.getEmail().equals(userUpdateVm.getEmail())) {
            update = true;
            userSystemDto.setEmail(userUpdateVm.getEmail());
        }
        if (userUpdateVm.getFullName() != null && !userSystemDto.getFullName().equals(userUpdateVm.getFullName())) {
            update = true;
            userSystemDto.setFullName(userUpdateVm.getFullName());
        }
        return update;
    }

    @Override
    public void deleteUserSystem(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("id must be not null");
        }
        userRepo.deleteById(id);
    }

    @Override
    public UserSystemDto updateRolesForUserSystem(Long userId, List<String> roleNames) {
        if (Objects.isNull(userId)) {
            throw new BadRequestException("user id must be not null");
        }
        UserSystemDto userSystemDto = getUserById(userId);
        List<RoleDto> rolesDto = roleNames.stream().map(roleService::getRoleByName).toList();
        //user system dto to user system
        UserSystem userSystem = UserMapper.INSTANCE.toUserSystem(userSystemDto);
        //role dto to role
        List<Role> newRoles = rolesDto.stream().map(RoleMapper.INSTANCE::toRole).toList();
        //get roles for this user
        userSystem.setRoles(newRoles);
        userSystem = userRepo.save(userSystem);
        return UserMapper.INSTANCE.toUserDto(userSystem);
    }

}
