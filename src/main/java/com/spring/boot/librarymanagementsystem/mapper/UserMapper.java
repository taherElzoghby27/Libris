package com.spring.boot.librarymanagementsystem.mapper;

import com.spring.boot.librarymanagementsystem.dto.UserSystemDto;
import com.spring.boot.librarymanagementsystem.entity.UserSystem;
import com.spring.boot.librarymanagementsystem.vm.user.UserResponseVm;
import com.spring.boot.librarymanagementsystem.vm.user.UserSystemSignUpVm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserSystemDto toUserDto(UserSystem user);

    UserSystem toUserSystem(UserSystemDto userSystemDto);

    UserSystem toUserSystem(UserSystemSignUpVm userSystemSignUpVm);

    UserResponseVm toUserResponseVm(UserSystemDto userSystemDto);

}
