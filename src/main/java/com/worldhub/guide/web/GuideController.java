package com.worldhub.guide.web;

import com.worldhub.guide.model.Guide;
import com.worldhub.guide.section.model.Section;
import com.worldhub.guide.section.service.SectionService;
import com.worldhub.guide.util.GuideMapper;
import com.worldhub.guide.service.GuideService;
import com.worldhub.guide.util.SectionMapper;
import com.worldhub.guide.web.dto.guide.CollectionResponse;
import com.worldhub.guide.web.dto.guide.GuideCreateRequest;
import com.worldhub.guide.web.dto.guide.GuideResponse;
import com.worldhub.guide.web.dto.guide.GuideUpdateRequest;
import com.worldhub.guide.web.dto.section.SectionCreateRequest;
import com.worldhub.guide.web.dto.section.SectionResponse;
import com.worldhub.guide.web.dto.section.SectionUpdateRequest;
import com.worldhub.guide.web.dto.tags.TagResponse;
import com.worldhub.guide.web.dto.tags.TagsCollectionResponse;
import jakarta.validation.Valid;
import library.AuthenticationMetadata;
import library.util.Unauthenticated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.worldhub.guide.web.ApiConstants.EndpointPaths.GUIDES;
import static com.worldhub.guide.web.ApiConstants.Versions.V1;

@RestController
@RequestMapping(path = V1 + GUIDES)
public class GuideController {

    private final GuideService guideService;
    private final SectionService sectionService;

    @Autowired
    public GuideController(GuideService guideService, SectionService sectionService) {
        this.guideService = guideService;
        this.sectionService = sectionService;
    }

    @PostMapping(produces = "application/vnd.world-hub.empty.v1+json")
    public ResponseEntity<Void> createGuide(@RequestBody @Valid GuideCreateRequest request,
                                                     @AuthenticationPrincipal AuthenticationMetadata metadata) {

        UUID ownerId = metadata.getUserId();

        Guide guide = guideService.create(request, ownerId);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(guide.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .build();
    }

    @GetMapping(path = "/{guideId}", produces = "application/vnd.world-hub.guide-collection.v1+json")
    public ResponseEntity<CollectionResponse<?>> getGuide(@PathVariable UUID guideId,
                                                          @AuthenticationPrincipal AuthenticationMetadata metadata) {

        UUID userId = metadata.getUserId();
        Guide guide = guideService.getById(guideId);

        CollectionResponse<?> response = guideService.determineResponse(guide, userId);

        return ResponseEntity.ok(response);
    }

    @Unauthenticated
    @GetMapping
    public ResponseEntity<List<GuideResponse>> getAllGuides() {

        List<Guide> guides = guideService.getAll();

        List<GuideResponse> response = guides.stream()
                .map(GuideMapper::mapToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{guideId}")
    public ResponseEntity<GuideResponse> updateGuide(@RequestBody @Valid GuideUpdateRequest request,
                                                     @PathVariable UUID guideId,
                                                     @AuthenticationPrincipal AuthenticationMetadata metadata) {

        UUID userId = metadata.getUserId();
        Guide guide = guideService.updateById(request, guideId, userId);
        GuideResponse response = GuideMapper.mapToResponse(guide);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // POST /guides/{guideId}/sections
    //--only base information with DTO
    // TODO: Vik, Please elaborate!
    @PostMapping("/{guideId}/sections")
    public ResponseEntity<SectionResponse> createSection(@PathVariable UUID guideId,
                                                         @RequestBody @Valid SectionCreateRequest request,
                                                         @AuthenticationPrincipal AuthenticationMetadata metadata) {

        UUID userId =  metadata.getUserId();
        Section section = sectionService.create(request, guideId, userId);
        SectionResponse response = SectionMapper.mapToResponse(section);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    //PUT /guides/sections/{sectionId}
    //--changes base information with DTO
    // TODO: Vik, Please elaborate!
    @PutMapping("/sections/{sectionId}")
    public ResponseEntity<SectionResponse> updateSectionById(@PathVariable UUID sectionId, @RequestBody SectionUpdateRequest request) {

        Section section = sectionService.updateSection(sectionId, request);
        SectionResponse response = SectionMapper.mapToResponse(section);

        return ResponseEntity.ok(response);
    }

    //PUT /guides/{guideId}/sections/{sectionId}/activities
    // TODO: Vik, Please elaborate!
    @GetMapping("/tags")
    public ResponseEntity<TagsCollectionResponse> getTags() {

        List<TagResponse> tags = guideService.getAllTags();
        TagsCollectionResponse response = GuideMapper.mapTagsToResponse(tags);

        return ResponseEntity.ok(response);
    }
}