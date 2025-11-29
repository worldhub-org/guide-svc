package com.worldhub.guide.web.dto.section;

import com.worldhub.guide.activity.model.Activity;
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
public class SectionResponse {

    private UUID id;
    private String title;
    private String description;
    private String imageUrl;
    private List<Activity> activities;
}
