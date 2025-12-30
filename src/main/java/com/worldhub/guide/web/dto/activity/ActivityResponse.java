package com.worldhub.guide.web.dto.activity;

import com.worldhub.guide.activity.model.ActivityType;
import com.worldhub.guide.activity.model.DifficultyLevel;
import com.worldhub.guide.activity.model.DurationUnit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResponse {

    private UUID id;
    private UUID userId;
    private String name;
    private String imageUrl;
    private String description;
    private String location;
    private String contactInfo;
    private String included;
    private String notIncluded;
    private String bringWithYou;
    private String safetyNotes;
    private List<String> dos;
    private List<String> donts;
    private String recommendations;
    private ActivityExpenseResponse expense;
    private ActivityType activityType;
    private DifficultyLevel difficultyLevel;
    private Integer durationValue;
    private DurationUnit durationUnit;
    private OffsetDateTime createdOn;
    private OffsetDateTime updatedOn;
}
