package com.worldhub.guide.web;

import com.worldhub.guide.util.SectionMapper;
import com.worldhub.guide.section.model.Section;
import com.worldhub.guide.section.service.SectionService;
import com.worldhub.guide.web.dto.section.SectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.worldhub.guide.web.ApiConstants.EndpointPaths.SECTIONS;
import static com.worldhub.guide.web.ApiConstants.Versions.V1;

@RestController
@RequestMapping(path = V1 + SECTIONS)
public class SectionController {

    private final SectionService sectionService;

    @Autowired
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }



    @PostMapping("/{sectionId}/activities")
    public ResponseEntity<SectionResponse> addActivityToSection(@PathVariable UUID sectionId, @PathVariable UUID activityId) {

        Section section = sectionService.addActivityToSection(sectionId, activityId);

        SectionResponse response = SectionMapper.mapToResponse(section);

        return ResponseEntity.ok(response);
    }
}
