package com.worldhub.guide.web.dto.section;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class SectionActivitiesRequest {

    private List<SectionActivityRequest> activities;
    private List<UUID> dropActivities;
}
