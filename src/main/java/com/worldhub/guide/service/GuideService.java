package com.worldhub.guide.service;

import com.worldhub.guide.exception.DomainException;
import com.worldhub.guide.exception.ForbiddenOperationException;
import com.worldhub.guide.exception.ResourceNotFoundException;
import com.worldhub.guide.model.CostType;
import com.worldhub.guide.model.Guide;
import com.worldhub.guide.model.GuideStatus;
import com.worldhub.guide.model.GuideTag;
import com.worldhub.guide.purchase.model.PurchasedGuide;
import com.worldhub.guide.purchase.repository.PurchasedGuideRepository;
import com.worldhub.guide.repository.GuideRepository;
import com.worldhub.guide.section.model.Section;
import com.worldhub.guide.util.GuideMapper;
import com.worldhub.guide.web.dto.guide.*;
import com.worldhub.guide.web.dto.tags.TagResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;

@Service
public class GuideService {

    private static final Logger logger = LoggerFactory.getLogger(GuideService.class);

    private final GuideRepository guideRepository;
    private final PurchasedGuideRepository purchasedGuideRepository;

    @Autowired
    public GuideService(GuideRepository guideRepository, PurchasedGuideRepository purchasedGuideRepository) {
        this.guideRepository = guideRepository;
        this.purchasedGuideRepository = purchasedGuideRepository;
    }

    public Guide create(GuideCreateRequest request, UUID ownerId) {

        Guide guide = GuideMapper.mapToGuide(request, ownerId);

        Guide persistedGuide = guideRepository.save(guide);

        logger.info("Guide with ID {} created by user {} with title '{}'.",
                persistedGuide.getId(), persistedGuide.getOwnerId(), persistedGuide.getTitle());

        return persistedGuide;
    }

    public Guide updateById(GuideUpdateRequest request, UUID guideId, UUID userId) {

        Guide guide = getById(guideId);

        validateUpdateRequest(request, userId, guide);
        applyUpdates(guide, request);

        Guide updatedGuide = guideRepository.save(guide);
        logger.info("Guide with ID [{}] updated.", updatedGuide.getId());

        return updatedGuide;
    }

    public Guide getById(UUID guideId) {

        return guideRepository.findById(guideId)
                .orElseThrow(() -> {
                    logger.warn("Guide with ID [{}] not found.", guideId);
                    return new ResourceNotFoundException(String.format("Guide with ID [%s] not found.", guideId));
                });
    }

    public List<Guide> getAll() {

        return guideRepository.findAll();
    }

    public void addSectionToGuide(Section section, UUID guideId) {

        Guide guide = getById(guideId);
        guide.getSections().add(section);

        guideRepository.save(guide);
        logger.info("Section with ID [{}] successfully added to guide with ID [{}]", section.getId(), guide.getId());
    }

    public List<TagResponse> getAllTags() {

        return Arrays.stream(GuideTag.values())
                .map(tag -> TagResponse.builder()
                        .enumValue(tag.name())
                        .enumTitle(tag.getTitle())
                        .description(tag.getDescription())
                        .build())
                .toList();
    }

    private void applyUpdates(Guide guide, GuideUpdateRequest request) {

        guide.setTitle(request.getTitle());
        guide.setDescription(request.getDescription());
        guide.setRecommendedFor(request.getRecommendedFor());
        guide.setCity(request.getCity());
        guide.setCountry(request.getCountry());
        guide.setCostType(request.getCostType());

        if (request.getCostType() == CostType.FREE) {
            guide.setPrice(BigDecimal.ZERO);
            guide.setCurrency(null);
        } else if (request.getCostType() == CostType.PAID) {
            guide.setPrice(request.getPrice());
            guide.setCurrency(request.getCurrency());
        }

        guide.setUpdatedOn(OffsetDateTime.now());
    }


    private void validateUpdateRequest(GuideUpdateRequest request, UUID userId, Guide guide) {

        if (!guide.getOwnerId().equals(userId)) {
            logger.warn("User with ID [{}] is not owner of guide ID [{}].", userId, guide.getId());
            throw new ForbiddenOperationException("User with ID [%s] is not owner of the guide.".formatted(userId));
        }

        if (request.getCostType() == CostType.FREE && (request.getPrice() != null || request.getCurrency() != null)) {
            logger.warn("FREE guides cannot have price or currency.");
            throw new DomainException("FREE guides cannot have price or currency.");
        }

        if (request.getPrice() != null && request.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            logger.warn("Price cannot be negative.");
            throw new DomainException("Price cannot be negative.");
        }

        if ((request.getPrice() != null || request.getCurrency() != null) &&
                (request.getCostType() != null && request.getCostType() != CostType.PAID) &&
                guide.getCostType() != CostType.PAID) {
            logger.warn("Price and currency can only be set for PAID guides");
            throw new DomainException("Price and currency can only be set for PAID guides");
        }
    }

    public CollectionResponse<?> determineResponse(Guide guideFromUrl, UUID userId) {

        boolean isOwner = guideFromUrl.getOwnerId().equals(userId);
        if (isOwner) {
            return ownerResponse(guideFromUrl);
        }

        // For non-owners, always authorize against and render the latest PUBLISHED version
        Guide latest = getLatestPublishedVersionOrThrow(guideFromUrl);

        boolean isFree = latest.getCostType() == CostType.FREE;
        boolean isPurchased = hasPurchaseForVersionKey(userId, latest.getVersionKey());

        if (isFree || isPurchased){
            return fullResponse(latest);
        }

        return previewResponse(latest);
    }

    private CollectionResponse<GuideResponse> ownerResponse(Guide guide) {

        List<GuideResponse> response = guideRepository.findAllByVersionKeyOrderByVersionDesc(guide.getVersionKey())
                .stream()
                .map(GuideMapper::mapToResponse)
                .toList();

        return CollectionResponse.<GuideResponse>builder()
                .collection(response)
                .build();
    }

    private CollectionResponse<GuideResponse> fullResponse(Guide guide) {

        GuideResponse response = GuideMapper.mapToResponse(guide);
        return CollectionResponse.<GuideResponse>builder()
                .collection(List.of(response))
                .build();
    }

    public Guide getLatestPublishedVersionOrThrow(Guide guide) {

        return guideRepository
                .findTopByVersionKeyAndStatusOrderByVersionDesc(guide.getVersionKey(), GuideStatus.PUBLISHED)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No published version available for guide " + guide.getId()
                ));
    }


    private CollectionResponse<GuidePreviewResponse> previewResponse(Guide guide) {

        GuidePreviewResponse preview = GuideMapper.mapToGuidePreview(guide);

        return CollectionResponse.<GuidePreviewResponse>builder()
                .collection(List.of(preview))
                .build();
    }

    private boolean hasPurchaseForVersionKey(UUID userId, UUID versionKey) {
        return purchasedGuideRepository.findByUserIdAndVersionKey(userId, versionKey).isPresent();
    }
}
