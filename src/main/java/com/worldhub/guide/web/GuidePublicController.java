package com.worldhub.guide.web;

import com.worldhub.guide.model.Guide;
import com.worldhub.guide.service.GuideService;
import com.worldhub.guide.util.GuideMapper;
import com.worldhub.guide.web.dto.guide.GuidePreviewCollection;
import com.worldhub.guide.web.dto.guide.GuidePreviewResponse;
import library.util.Unauthenticated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.worldhub.guide.web.ApiConstants.EndpointPaths.PUBLIC_GUIDES;
import static com.worldhub.guide.web.ApiConstants.Versions.V1;

@Unauthenticated
@RestController
@RequestMapping(path = V1 + PUBLIC_GUIDES)
public class GuidePublicController {

    private final GuideService guideService;

    public GuidePublicController(GuideService guideService) {
        this.guideService = guideService;
    }

    @GetMapping(produces = "application/vnd.world-hub-guide-collection.v1+json")
    public ResponseEntity<GuidePreviewCollection> getAllGuides() {

        List<Guide> guides = guideService.getAll();

        GuidePreviewCollection response = GuideMapper.mapToGuidePreviewCollection(guides);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path ="/{guideId}", produces = "application/vnd.world-hub-guide.v1+json")
    public ResponseEntity<GuidePreviewResponse> getGuideById(@PathVariable UUID guideId) {

        Guide guideFromUrl = guideService.getById(guideId);
        Guide latestPublished = guideService.getLatestPublishedVersionOrThrow(guideFromUrl);
        GuidePreviewResponse response = GuideMapper.mapToGuidePreview(latestPublished);

        return ResponseEntity.ok(response);
    }
}
