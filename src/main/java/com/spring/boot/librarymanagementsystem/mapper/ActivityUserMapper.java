package com.spring.boot.librarymanagementsystem.mapper;

import com.spring.boot.librarymanagementsystem.dto.ActivityUserDto;
import com.spring.boot.librarymanagementsystem.entity.ActivityUser;
import com.spring.boot.librarymanagementsystem.vm.activity.ActivityRequestVm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UserMapper.class})
public interface ActivityUserMapper {
    ActivityUserMapper INSTANCE = Mappers.getMapper(ActivityUserMapper.class);

    ActivityUserDto toActivityUserDto(ActivityUser activityUser);

    @Mapping(source = "userId", target = "user", ignore = true)
    ActivityUser toActivityUser(ActivityRequestVm activityRequestVm);
}
