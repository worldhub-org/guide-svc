package com.worldhub.guide.web.dto.activity;

import com.worldhub.guide.activity.model.ActivityType;
import com.worldhub.guide.activity.model.DifficultyLevel;
import com.worldhub.guide.activity.model.DurationUnit;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityCreateRequest {

    @NotNull(message = "User ID is required")
    private UUID userId;
    @NotNull(message = "Name is required")
    private String name;
    private String imageUrl;
    @NotNull(message = "Description is required")
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
    @NotNull(message = "Activity type is required")
    private ActivityType activityType;
    @NotNull(message = "Difficulty level is required")
    private DifficultyLevel difficultyLevel;
    @NotNull(message = "Duration value is required")
    private Integer durationValue;
    @NotNull(message = "Duration unit is required")
    private DurationUnit durationUnit;
}
