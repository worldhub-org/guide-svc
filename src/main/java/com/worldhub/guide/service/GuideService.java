package com.worldhub.guide.service;

import com.worldhub.guide.api.dto.guide.InitializeGuideRequest;
import com.worldhub.guide.model.Guide;
import com.worldhub.guide.model.GuideStatus;
import com.worldhub.guide.repository.GuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class GuideService {

    private final GuideRepository guideRepository;

    @Autowired
    public GuideService(GuideRepository guideRepository) {
        this.guideRepository = guideRepository;
    }

    public Guide initialize(InitializeGuideRequest request, UUID creatorId, String creatorEmail) {

        Guide guide = Guide.builder()
                .title(request.title())
                .shortDescription(request.shortDescription())
                .destination(request.destination())
                .country(request.country())
                .costType(request.costType())
                .status(GuideStatus.DRAFT)
                .version(1)
                .ownerId(creatorId)
                .ownerEmail(creatorEmail)
                .createdOn(OffsetDateTime.now())
                .updatedOn(OffsetDateTime.now())
                .build();

        return guideRepository.save(guide);
    }
}
