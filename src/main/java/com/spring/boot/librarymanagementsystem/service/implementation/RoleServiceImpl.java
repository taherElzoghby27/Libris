package com.spring.boot.librarymanagementsystem.service.implementation;

import com.spring.boot.librarymanagementsystem.dto.RoleDto;
import com.spring.boot.librarymanagementsystem.entity.Role;
import com.spring.boot.librarymanagementsystem.exception.custom_exception.NotFoundResourceException;
import com.spring.boot.librarymanagementsystem.mapper.RoleMapper;
import com.spring.boot.librarymanagementsystem.repository.RoleRepo;
import com.spring.boot.librarymanagementsystem.service.RoleService;
import com.spring.boot.librarymanagementsystem.utils.enums.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;

    @Override
    public RoleDto getRoleByName(String role) {
        RoleType roleType = RoleType.valueOf(role);
        Role roleResult = roleRepo.findByRole(roleType);
        if (Objects.isNull(roleResult)) {
            throw new NotFoundResourceException("Role not found");
        }
        return RoleMapper.INSTANCE.toDto(roleResult);
    }
}
