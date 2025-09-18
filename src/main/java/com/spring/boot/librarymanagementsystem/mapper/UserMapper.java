package com.spring.boot.librarymanagementsystem.mapper;

import com.spring.boot.librarymanagementsystem.dto.UserSystemDto;
import com.spring.boot.librarymanagementsystem.entity.UserSystem;
import com.spring.boot.librarymanagementsystem.vm.UserSystemRequestVm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserSystemDto toUserDto(UserSystem user);

    UserSystem toUserSystem(UserSystemDto userSystemDto);

    UserSystem toUserSystem(UserSystemRequestVm userSystemRequestVm);

}
