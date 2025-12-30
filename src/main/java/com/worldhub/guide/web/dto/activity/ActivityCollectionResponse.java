package com.worldhub.guide.web.dto.activity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ActivityCollectionResponse {

    private List<ActivityResponse> activities;
}
