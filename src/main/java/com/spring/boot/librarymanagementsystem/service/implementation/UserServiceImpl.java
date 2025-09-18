package com.spring.boot.librarymanagementsystem.service.implementation;

import com.spring.boot.librarymanagementsystem.dto.RoleDto;
import com.spring.boot.librarymanagementsystem.dto.UserSystemDto;
import com.spring.boot.librarymanagementsystem.entity.Role;
import com.spring.boot.librarymanagementsystem.entity.UserSystem;
import com.spring.boot.librarymanagementsystem.exception.BadRequestException;
import com.spring.boot.librarymanagementsystem.exception.NotFoundResourceException;
import com.spring.boot.librarymanagementsystem.mapper.RoleMapper;
import com.spring.boot.librarymanagementsystem.mapper.UserMapper;
import com.spring.boot.librarymanagementsystem.repository.UserRepo;
import com.spring.boot.librarymanagementsystem.service.PaginationService;
import com.spring.boot.librarymanagementsystem.service.RoleService;
import com.spring.boot.librarymanagementsystem.service.UserService;
import com.spring.boot.librarymanagementsystem.utils.RoleType;
import com.spring.boot.librarymanagementsystem.vm.UserSystemRequestVm;
import com.spring.boot.librarymanagementsystem.vm.UsersResponseVm;
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
                result.getContent().stream().map(UserMapper.INSTANCE::toUserDto).toList(),
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
    public UserSystemDto createUserSystem(UserSystemRequestVm userSystemRequestVm) {
        if (Objects.nonNull(userSystemRequestVm.getId())) {
            throw new BadRequestException("id must be null");
        }
        UserSystem userSystem = UserMapper.INSTANCE.toUserSystem(userSystemRequestVm);
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
    public UserSystemDto updateUserSystem(UserSystemRequestVm userSystemRequestVm) {
        boolean update = false;
        if (Objects.isNull(userSystemRequestVm.getId())) {
            throw new BadRequestException("id must be not null");
        }
        UserSystemDto userSystemDto = getUserById(userSystemRequestVm.getId());
        if (!userSystemDto.getUsername().equals(userSystemRequestVm.getUsername())) {
            update = true;
            userSystemDto.setUsername(userSystemRequestVm.getUsername());
        }
        if (!userSystemDto.getPassword().equals(userSystemRequestVm.getPassword())) {
            update = true;
            userSystemDto.setPassword(userSystemRequestVm.getPassword());
        }
        if (!userSystemDto.getEmail().equals(userSystemRequestVm.getEmail())) {
            update = true;
            userSystemDto.setEmail(userSystemRequestVm.getEmail());
        }
        if (!userSystemDto.getFullName().equals(userSystemRequestVm.getFullName())) {
            update = true;
            userSystemDto.setFullName(userSystemRequestVm.getFullName());
        }
        if (!update) {
            throw new NotFoundResourceException("data must be different");
        }
        UserSystem userSystem = UserMapper.INSTANCE.toUserSystem(userSystemDto);
        userSystem.setPassword(passwordEncoder.encode(userSystem.getPassword()));
        userSystem = userRepo.save(userSystem);
        return UserMapper.INSTANCE.toUserDto(userSystem);
    }

    @Override
    public void deleteUserSystem(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("id must be not null");
        }
        userRepo.deleteById(id);
    }

}
