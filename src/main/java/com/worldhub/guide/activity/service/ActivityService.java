package com.worldhub.guide.activity.service;

import com.worldhub.guide.activity.model.Activity;
import com.worldhub.guide.activity.model.ActivityExpense;
import com.worldhub.guide.activity.repository.ActivityRepository;
import com.worldhub.guide.exception.ResourceNotFoundException;
import com.worldhub.guide.util.ActivityMapper;
import com.worldhub.guide.web.dto.activity.ActivityCreateRequest;
import com.worldhub.guide.web.dto.activity.ActivityUpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ActivityService {

    private static final Logger log = LoggerFactory.getLogger(ActivityService.class);

    private final ActivityRepository activityRepository;
    private final ActivityExpenseService activityExpenseService;

    public ActivityService(ActivityRepository activityRepository, ActivityExpenseService activityExpenseService) {
        this.activityRepository = activityRepository;
        this.activityExpenseService = activityExpenseService;
    }

    public Activity create(ActivityCreateRequest request, UUID userId) {

        Activity activity = ActivityMapper.mapToActivity(request, userId);

        Activity persistedActivity = activityRepository.save(activity);
        log.info("Section with ID {} created successfully.", persistedActivity.getId());

        ActivityExpense activityExpense = activityExpenseService.createExpense(activity.getId());
        persistedActivity.setExpense(activityExpense);
        activityRepository.save(activity);

        return persistedActivity;
    }

    public List<Activity> getAllByUserId(UUID userId) {

        return activityRepository.findAllByUserId(userId);
    }

    public Activity getById(UUID activityId) {

        return activityRepository.findById(activityId)
                .orElseThrow(() -> {
                    log.warn("Activity with ID {} not found.", activityId);
                    return new ResourceNotFoundException(String.format("Activity with ID [%s] not found.", activityId));
                });
    }

    public Activity update(ActivityUpdateRequest request, UUID activityId) {

        Activity activity = getById(activityId);

        ActivityMapper.applyUpdates(activity, request);

        Activity updatedActivity = activityRepository.save(activity);
        log.info("Activity with ID {} successfully updated.", activityId);

        return updatedActivity;
    }
}