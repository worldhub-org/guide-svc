package com.worldhub.guide.service;

import com.worldhub.guide.exception.DomainException;
import com.worldhub.guide.exception.ResourceNotFoundException;
import com.worldhub.guide.model.CostType;
import com.worldhub.guide.model.Guide;
import com.worldhub.guide.model.GuideTag;
import com.worldhub.guide.repository.GuideRepository;
import com.worldhub.guide.section.model.Section;
import com.worldhub.guide.util.GuideMapper;
import com.worldhub.guide.web.dto.guide.GuideCreateRequest;
import com.worldhub.guide.web.dto.guide.GuideUpdateRequest;
import com.worldhub.guide.web.dto.tags.TagResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class GuideService {

    private static final Logger log = LoggerFactory.getLogger(GuideService.class);

    private final GuideRepository guideRepository;

    @Autowired
    public GuideService(GuideRepository guideRepository) {
        this.guideRepository = guideRepository;
    }

    public Guide create(GuideCreateRequest request, UUID ownerId) {

        Guide guide = GuideMapper.mapToGuide(request, ownerId);

        Guide persistedGuide = guideRepository.save(guide);

        log.info("Guide with ID {} created by user {} with title '{}'.",
                persistedGuide.getId(), persistedGuide.getOwnerId(), persistedGuide.getTitle());

        return persistedGuide;
    }

    public Guide updateGuideById(GuideUpdateRequest request, UUID guideId) {

        Guide guide = getById(guideId);

        if (request.getCostType() == CostType.FREE &&
                (request.getPrice() != null || request.getCurrency() != null)) {
            log.warn("FREE guides cannot have price or currency.");
            throw new DomainException("FREE guides cannot have price or currency.");
        }

        if (request.getPrice() != null && request.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            log.warn("Price cannot be negative.");
            throw new DomainException("Price cannot be negative.");
        }

        if ((request.getPrice() != null || request.getCurrency() != null) &&
                (request.getCostType() != null && request.getCostType() != CostType.PAID) &&
                guide.getCostType() != CostType.PAID) {
            log.warn("Price and currency can only be set for PAID guides");
            throw new DomainException("Price and currency can only be set for PAID guides");
        }

        GuideMapper.applyUpdates(guide, request);

        Guide updatedGuide = guideRepository.save(guide);
        log.info("Guide with ID {} updated.", updatedGuide.getId());

        return updatedGuide;
    }

    public Guide getById(UUID guideId) {

        return guideRepository.findById(guideId)
                .orElseThrow(() -> {
                    log.warn("Guide with ID {} not found.", guideId);
                    return new ResourceNotFoundException(String.format("Guide with ID [%s] not found.", guideId));
                });
    }

    public List<Guide> findAll() {

        return guideRepository.findAll();
    }

    public void addSectionToGuide(Section section, UUID guideId) {

        Guide guide = getById(guideId);
        guide.getSections().add(section);

        guideRepository.save(guide);
        log.info("Section with ID {} successfully added to guide with ID {}", section.getId(), guide.getId());
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
}
