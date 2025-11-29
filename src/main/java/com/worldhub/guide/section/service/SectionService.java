package com.worldhub.guide.section.service;

import com.worldhub.guide.activity.model.Activity;
import com.worldhub.guide.activity.service.ActivityService;
import com.worldhub.guide.exception.ResourceNotFoundException;
import com.worldhub.guide.util.SectionMapper;
import com.worldhub.guide.section.model.Section;
import com.worldhub.guide.section.repository.SectionRepository;
import com.worldhub.guide.service.GuideService;
import com.worldhub.guide.web.dto.section.SectionCreateRequest;
import com.worldhub.guide.web.dto.section.SectionUpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SectionService {

    private static final Logger log = LoggerFactory.getLogger(SectionService.class);

    private final SectionRepository sectionRepository;
    private final GuideService guideService;
    private final ActivityService activityService;

    public SectionService(SectionRepository sectionRepository, GuideService guideService, ActivityService activityService) {
        this.sectionRepository = sectionRepository;
        this.guideService = guideService;
        this.activityService = activityService;
    }

    public Section create(UUID guideId, SectionCreateRequest request) {

        Section section = SectionMapper.mapToSection(request);

        Section persistedSection = sectionRepository.save(section);
        log.info("Section with ID {} created successfully.", persistedSection.getId());

        guideService.addSectionToGuide(persistedSection, guideId);

        return persistedSection;
    }

    public Section getSectionById(UUID sectionId) {

        return sectionRepository.findById(sectionId)
                .orElseThrow(() -> {
                    log.warn("Section with ID {} not found.", sectionId);
                    return new ResourceNotFoundException(String.format("Section with ID [%s] not found.", sectionId));
                });
    }

    public Section updateSection(UUID sectionId,SectionUpdateRequest request) {

        Section section = getSectionById(sectionId);

        Section updatedSection = sectionRepository.save(section);
        log.info("Section with ID {} updated.", updatedSection.getId());

        return updatedSection;
    }

    public Section addActivityToSection(UUID sectionId, UUID activityId) {

        Activity activity = activityService.getActivityById(activityId);
        Section section = getSectionById(sectionId);

        //section.getActivities().add(activity);

        Section updatedSection = sectionRepository.save(section);
        log.info("Activity with ID {} successfully added to Section with ID {}", activityId, sectionId);

        return updatedSection;
    }

}
