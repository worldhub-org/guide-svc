package com.worldhub.guide.web;

import com.worldhub.guide.activity.model.Activity;
import com.worldhub.guide.util.ActivityMapper;
import com.worldhub.guide.activity.service.ActivityService;
import com.worldhub.guide.web.dto.activity.ActivityCollectionResponse;
import com.worldhub.guide.web.dto.activity.ActivityCreateRequest;
import com.worldhub.guide.web.dto.activity.ActivityResponse;
import com.worldhub.guide.web.dto.activity.ActivityUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.worldhub.guide.web.ApiConstants.EndpointPaths.ACTIVITIES;
import static com.worldhub.guide.web.ApiConstants.Versions.V1;

@RestController
@RequestMapping(path = V1 + ACTIVITIES)
public class ActivityController {

    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public ResponseEntity<ActivityCollectionResponse> getAllActivities(@PathVariable UUID userId) {

        List<ActivityResponse> activities = activityService.getAllByUserId(userId).stream()
                .map(ActivityMapper::mapToResponse).toList();

        ActivityCollectionResponse response = ActivityCollectionResponse.builder()
                .activities(activities)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{activityId}")
    public ResponseEntity<ActivityResponse> getActivityById(@PathVariable UUID activityId) {

        Activity activity = activityService.getActivityById(activityId);

        ActivityResponse response = ActivityMapper.mapToResponse(activity);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ActivityResponse> createActivity(@RequestBody @Valid ActivityCreateRequest request) {

        Activity activity = activityService.createActivity(request);

        ActivityResponse response = ActivityMapper.mapToResponse(activity);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{activityId}")
    public ResponseEntity<ActivityResponse> updateActivity(@RequestBody ActivityUpdateRequest request, @PathVariable UUID activityId) {

        Activity activity = activityService.updateActivity(request, activityId);

        ActivityResponse response = ActivityMapper.mapToResponse(activity);

        return ResponseEntity.ok(response);
    }
}
