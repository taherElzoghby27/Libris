package com.spring.boot.librarymanagementsystem.service.implementation;

import com.spring.boot.librarymanagementsystem.dto.UserSystemDto;
import com.spring.boot.librarymanagementsystem.entity.UserSystem;
import com.spring.boot.librarymanagementsystem.exception.BadRequestException;
import com.spring.boot.librarymanagementsystem.exception.NotFoundResourceException;
import com.spring.boot.librarymanagementsystem.mapper.UserMapper;
import com.spring.boot.librarymanagementsystem.repository.UserRepo;
import com.spring.boot.librarymanagementsystem.service.PaginationService;
import com.spring.boot.librarymanagementsystem.service.UserService;
import com.spring.boot.librarymanagementsystem.vm.UsersResponseVm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

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

}
