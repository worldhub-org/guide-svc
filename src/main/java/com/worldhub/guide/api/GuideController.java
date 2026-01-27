package com.worldhub.guide.api;

import com.worldhub.guide.api.dto.guide.GuideBasicResponse;
import com.worldhub.guide.api.dto.guide.GuideMapper;
import com.worldhub.guide.api.dto.guide.InitializeGuideRequest;
import com.worldhub.guide.model.Guide;
import com.worldhub.guide.service.GuideService;
import library.AuthenticationMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/guides")
public class GuideController {

    private final GuideService guideService;

    @Autowired
    public GuideController(GuideService guideService) {
        this.guideService = guideService;
    }

    @PostMapping
    public ResponseEntity<GuideBasicResponse> initializeGuide(@RequestBody @Validated InitializeGuideRequest request,
                                                              @AuthenticationPrincipal AuthenticationMetadata metadata) {

        Guide guide = guideService.initialize(request, metadata.getUserId(), metadata.getEmail());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(guide.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(GuideMapper.toBasicResponse(guide));
    }
}
