package com.worldhub.guide.api.dto.guide;

import com.worldhub.guide.model.Guide;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GuideMapper {

    public static GuideBasicResponse toBasicResponse(Guide guide) {

        return GuideBasicResponse.builder()
                .id(guide.getId())
                .ownerId(guide.getOwnerId())
                .ownerEmail(guide.getOwnerEmail())
                .title(guide.getTitle())
                .shortDescription(guide.getShortDescription())
                .destination((guide.getDestination()))
                .country(guide.getCountry())
                .status(guide.getStatus())
                .version(guide.getVersion())
                .createdOn(guide.getCreatedOn())
                .updatedOn(guide.getUpdatedOn())
                .build();
    }
}
