package com.spring.boot.librarymanagementsystem.service;

import com.spring.boot.librarymanagementsystem.dto.RoleDto;

public interface RoleService {
    RoleDto getRoleByName(String role);
}
