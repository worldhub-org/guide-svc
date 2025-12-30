package com.worldhub.guide.web.dto.section;

import lombok.Data;

import java.util.UUID;

@Data
public class SectionActivityRequest {

    private UUID activityId;
    private Integer orderIndex;
}
