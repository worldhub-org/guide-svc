package com.worldhub.guide.web.dto.guide;

import com.worldhub.guide.model.CostType;
import com.worldhub.guide.model.GuideStatus;
import com.worldhub.guide.model.GuideTag;
import com.worldhub.guide.web.dto.section.SectionResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuideResponse {

    private UUID id;
    private UUID ownerId;
    private String title;
    private String description;
    private List<GuideTag> recommendedFor;
    private String city;
    private String country;
    private CostType costType;
    private BigDecimal price;
    private Currency currency;
    private Double averageRate;
    private List<SectionResponse> sections;
    private boolean isPublished;
    private OffsetDateTime createdOn;
    private OffsetDateTime updatedOn;
    private GuideStatus status;
    private double version;
    private boolean isDeleted;
}
