package com.worldhub.guide.util;

import com.worldhub.guide.model.Guide;
import com.worldhub.guide.model.GuideStatus;
import com.worldhub.guide.web.dto.guide.*;
import com.worldhub.guide.web.dto.section.SectionResponse;
import com.worldhub.guide.web.dto.tags.TagResponse;
import com.worldhub.guide.web.dto.tags.TagsCollectionResponse;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GuideMapper {
    public static Guide mapToGuide(GuideCreateRequest request, UUID ownerId) {

        return Guide.builder()
                .ownerId(ownerId)
                .title(request.getTitle())
                .description(request.getDescription())
                .recommendedFor(request.getRecommendedFor())
                .city(request.getCity())
                .country(request.getCountry())
                .costType(request.getCostType())
                .price(request.getPrice())
                .currency(request.getCurrency())
                .averageRate(0.0)
                .sections(new ArrayList<>())
                .version(1)
                .versionKey(UUID.randomUUID())
                .status(GuideStatus.IN_DEVELOPMENT)
                .isDeleted(false)
                .reviews(new ArrayList<>())
                .createdOn(OffsetDateTime.now())
                .updatedOn(OffsetDateTime.now())
                .build();
    }

    public static GuideResponse mapToResponse(Guide guide) {

        List<SectionResponse> sections = guide.getSections()
                .stream()
                .map(SectionMapper::mapToResponse)
                .toList();

        return GuideResponse.builder()
                .id(guide.getId())
                .ownerId(guide.getOwnerId())
                .title(guide.getTitle())
                .description(guide.getDescription())
                .recommendedFor(guide.getRecommendedFor())
                .city(guide.getCity())
                .country(guide.getCountry())
                .costType(guide.getCostType())
                .price(guide.getPrice())
                .currency(guide.getCurrency())
                .averageRate(guide.getAverageRate())
                .sections(sections)
                .version(guide.getVersion())
                .isDeleted(guide.isDeleted())
                .createdOn(guide.getCreatedOn())
                .updatedOn(guide.getUpdatedOn())
                .build();
    }

    public static TagsCollectionResponse mapTagsToResponse(List<TagResponse> tags) {

        return TagsCollectionResponse.builder()
                .tags(tags)
                .build();
    }

    public static GuidePreviewCollection mapToGuidePreviewCollection(List<Guide> guides) {

        List<GuidePreviewResponse> previews = guides.stream()
                .map(GuideMapper::mapToGuidePreview).toList();

        return GuidePreviewCollection.builder()
                .previews(previews)
                .build();
    }

    public static GuidePreviewResponse mapToGuidePreview(Guide g) {

        return GuidePreviewResponse.builder()
                .id(g.getId())
                .ownerId(g.getOwnerId())
                .title(g.getTitle())
                .description(g.getDescription())
                .recommendedFor(g.getRecommendedFor())
                .city(g.getCity())
                .country(g.getCountry())
                .costType(g.getCostType())
                .price(g.getPrice())
                .currency(g.getCurrency())
                .averageRate(g.getAverageRate())
                .createdOn(g.getCreatedOn())
                .updatedOn(g.getUpdatedOn())
                .status(g.getStatus())
                .build();
    }
}
