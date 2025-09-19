package com.spring.boot.librarymanagementsystem.service;

import com.spring.boot.librarymanagementsystem.vm.activity.ActivitiesResponseVm;
import com.spring.boot.librarymanagementsystem.dto.ActivityUserDto;
import com.spring.boot.librarymanagementsystem.vm.activity.ActivityRequestVm;

public interface ActivityUserService {
    ActivitiesResponseVm getActivities(int page, int size);

    void addActivity(ActivityRequestVm activityRequestVm);
}
