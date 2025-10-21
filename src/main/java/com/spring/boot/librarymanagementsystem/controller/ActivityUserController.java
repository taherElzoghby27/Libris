package com.spring.boot.librarymanagementsystem.controller;

import com.spring.boot.librarymanagementsystem.dto.SuccessDto;
import com.spring.boot.librarymanagementsystem.service.ActivityUserService;
import com.spring.boot.librarymanagementsystem.vm.activity.ActivitiesResponseVm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
public class ActivityUserController {

    private final ActivityUserService activityUserService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SuccessDto<ResponseEntity<ActivitiesResponseVm>> getActivities(@RequestParam int page, @RequestParam int size) {
        return new SuccessDto<>(
                ResponseEntity.ok(activityUserService.getActivities(page, size))
        );
    }
}
