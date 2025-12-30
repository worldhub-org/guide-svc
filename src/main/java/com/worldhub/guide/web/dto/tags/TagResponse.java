package com.worldhub.guide.web.dto.tags;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagResponse {

    private String enumValue;
    private String enumTitle;
    private String description;
}
