package com.spring.boot.librarymanagementsystem.mapper;

import com.spring.boot.librarymanagementsystem.dto.UserDto;
import com.spring.boot.librarymanagementsystem.entity.UserSystem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toUserDto(UserSystem user);

    UserSystem toUserSystem(UserDto userDto);

}
