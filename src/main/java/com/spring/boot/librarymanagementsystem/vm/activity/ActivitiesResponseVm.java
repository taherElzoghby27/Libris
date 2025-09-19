package com.spring.boot.librarymanagementsystem.vm.activity;

import com.spring.boot.librarymanagementsystem.dto.ActivityUserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ActivitiesResponseVm {
    private List<ActivityUserDto> activities;
    private Long size;
}
