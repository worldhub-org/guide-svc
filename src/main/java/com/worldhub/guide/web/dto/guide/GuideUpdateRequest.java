package com.worldhub.guide.web.dto.guide;

import com.worldhub.guide.model.CostType;
import com.worldhub.guide.model.GuideTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuideUpdateRequest {

    private String title;
    private String description;
    private List<GuideTag> recommendedFor;
    private String city;
    private String country;
    private CostType costType;
    private BigDecimal price;
    private Currency currency;
}
