package com.worldhub.guide.util;

import com.worldhub.guide.section.model.Section;
import com.worldhub.guide.web.dto.section.SectionCreateRequest;
import com.worldhub.guide.web.dto.section.SectionResponse;


public class SectionMapper {

    public static Section mapToSection(SectionCreateRequest request) {

        return Section.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .orderIndex(request.getOrderIndex())
                .build();
    }

    public static SectionResponse mapToResponse(Section section) {

        return SectionResponse.builder()
                .id(section.getId())
                .title(section.getTitle())
                .description(section.getDescription())
                //.activities(section.getActivities())
                .build();
    }

}
