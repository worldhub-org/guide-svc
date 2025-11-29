package com.worldhub.guide.util;

import com.worldhub.guide.activity.model.Activity;
import com.worldhub.guide.web.dto.activity.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ActivityMapper {
    public static Activity mapToActivity(ActivityCreateRequest request, UUID userId) {

        return Activity.builder()
                .userId(userId)
                .name(request.getName())
                .description(request.getDescription())
                //.location(request.getLocation())
                //.contactInfo(request.getContactInfo())
                //.included(request.getIncluded())
               // .notIncluded(request.getNotIncluded())
               // .bringWithYou(request.getBringWithYou())
              //  .safetyNotes(request.getSafetyNotes())
              //  .dos(request.getDos())
              //  .donts(request.getDonts())
                //.recommendations(request.getRecommendations())
                .activityType(request.getActivityType())
                .difficultyLevel(request.getDifficultyLevel())
                .durationValue(request.getDurationValue())
                .durationUnit(request.getDurationUnit())
                .createdOn(OffsetDateTime.now())
                .updatedOn(OffsetDateTime.now())
                .build();
    }

    public static ActivityResponse mapToResponse(Activity activity) {

        List<ActivityExpenseLineResponse> lines = new ArrayList<>();

        if (activity.getExpense().getLines() != null) {
            lines = activity
                    .getExpense()
                    .getLines()
                    .stream()
                    .map(line -> ActivityExpenseLineResponse.builder()
                            .id(line.getId())
                            .title(line.getTitle())
                            .notes(line.getNotes())
                            .amount(line.getAmount())
                            .createdOn(line.getCreatedOn())
                            .updatedOn(line.getUpdatedOn())
                            .build()).toList();
        }

        ActivityExpenseResponse expense = ActivityExpenseResponse.builder()
                .id(activity.getExpense().getId())
                .currency(activity.getExpense().getCurrency())
                .totalAmount(activity.getExpense().getTotalAmount())
                .lines(lines)
                .build();

        return ActivityResponse.builder()
                .id(activity.getId())
                .userId(activity.getUserId())
                .name(activity.getName())
/*                .imageUrl(activity.getImageUrl())
                .description(activity.getDescription())
                .location(activity.getLocation())
                .contactInfo(activity.getContactInfo())
                .included(activity.getIncluded())
                .notIncluded(activity.getNotIncluded())
                .bringWithYou(activity.getBringWithYou())
                .safetyNotes(activity.getSafetyNotes())
                .dos(activity.getDos())
                .donts(activity.getDonts())
                .recommendations(activity.getRecommendations())*/
                .expense(expense)
                .activityType(activity.getActivityType())
                .difficultyLevel(activity.getDifficultyLevel())
                .durationValue(activity.getDurationValue())
                .durationUnit(activity.getDurationUnit())
                .createdOn(activity.getCreatedOn())
                .updatedOn(activity.getUpdatedOn())
                .build();
    }

    public static void applyUpdates(Activity activity, ActivityUpdateRequest request) {

        activity.setName(request.getName());
/*        activity.setImageUrl(request.getImageUrl());
        activity.setDescription(request.getDescription());
        activity.setLocation(request.getLocation());
        activity.setContactInfo(request.getContactInfo());
        activity.setIncluded(request.getIncluded());
        activity.setNotIncluded(request.getNotIncluded());
        activity.setBringWithYou(request.getBringWithYou());
        activity.setSafetyNotes(request.getSafetyNotes());
        request.getDos().forEach(e -> activity.getDos().add(e));
        request.getDonts().forEach(e -> activity.getDonts().add(e));
        activity.setRecommendations(request.getRecommendations());*/
        activity.setActivityType(request.getActivityType());
        activity.setDifficultyLevel(request.getDifficultyLevel());
        activity.setDurationValue(request.getDurationValue());

        activity.setUpdatedOn(OffsetDateTime.now());
    }
}
