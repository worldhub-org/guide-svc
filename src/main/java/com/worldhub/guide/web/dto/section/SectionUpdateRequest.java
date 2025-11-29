package com.worldhub.guide.web.dto.section;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SectionUpdateRequest {

    private String title;
    private String description;
    private String imageUrl;
}
