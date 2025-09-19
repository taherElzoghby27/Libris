package com.spring.boot.librarymanagementsystem.mapper;

import com.spring.boot.librarymanagementsystem.dto.ActivityUserDto;
import com.spring.boot.librarymanagementsystem.entity.ActivityUser;
import com.spring.boot.librarymanagementsystem.vm.activity.ActivityRequestVm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UserMapper.class})
public interface ActivityUserMapper {
    ActivityUserMapper INSTANCE = Mappers.getMapper(ActivityUserMapper.class);

    ActivityUserDto toActivityUserDto(ActivityUser activityUser);

    ActivityUser toActivityUser(ActivityRequestVm activityRequestVm);
}
