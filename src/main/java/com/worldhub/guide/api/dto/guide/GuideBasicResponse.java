package com.worldhub.guide.api.dto.guide;

import com.worldhub.guide.model.GuideStatus;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
public record GuideBasicResponse(
        UUID id,
        UUID ownerId,
        String ownerEmail,
        String title,
        String shortDescription,
        String destination,
        String country,
        Integer version,
        GuideStatus status,
        OffsetDateTime createdOn,
        OffsetDateTime updatedOn
) { }
