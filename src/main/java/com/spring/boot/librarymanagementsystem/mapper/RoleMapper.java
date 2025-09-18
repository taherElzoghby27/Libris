package com.spring.boot.librarymanagementsystem.mapper;

import com.spring.boot.librarymanagementsystem.dto.RoleDto;
import com.spring.boot.librarymanagementsystem.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDto toDto(Role role);

    Role toRole(RoleDto roleDto);
}
