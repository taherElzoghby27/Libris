package com.spring.boot.librarymanagementsystem.service.implementation;

import com.spring.boot.librarymanagementsystem.entity.ActivityUser;
import com.spring.boot.librarymanagementsystem.exception.custom_exception.NotFoundResourceException;
import com.spring.boot.librarymanagementsystem.mapper.ActivityUserMapper;
import com.spring.boot.librarymanagementsystem.repository.ActivityUserRepo;
import com.spring.boot.librarymanagementsystem.service.ActivityUserService;
import com.spring.boot.librarymanagementsystem.service.PaginationService;
import com.spring.boot.librarymanagementsystem.vm.activity.ActivitiesResponseVm;
import com.spring.boot.librarymanagementsystem.vm.activity.ActivityRequestVm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ActivityUserServiceImpl implements ActivityUserService {

    private final ActivityUserRepo activityUserRepo;

    @Override
    public ActivitiesResponseVm getActivities(int page, int size) {
        Pageable pageable = PaginationService.getPageable(page, size);
        Page<ActivityUser> result = activityUserRepo.findAll(pageable);
        if (result.isEmpty()) {
            throw new NotFoundResourceException("activities not found");
        }
        return new ActivitiesResponseVm(
                result.map(ActivityUserMapper.INSTANCE::toActivityUserDto).getContent(),
                result.getTotalElements()
        );
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void addActivity(ActivityRequestVm activityRequestVm) {
        ActivityUser activityUser = ActivityUserMapper.INSTANCE.toActivityUser(activityRequestVm);
        activityUserRepo.save(activityUser);
    }
}
