package com.worldhub.guide.web.dto.guide;

import com.worldhub.guide.model.CostType;
import com.worldhub.guide.model.GuideTag;
import jakarta.validation.constraints.*;
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
public class GuideCreateRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title cannot exceed 255 characters")
    private String title;

    @Size(max = 5000, message = "Description cannot exceed 5000 characters")
    private String description;

    @Size(max = 5000, message = "Tags for recommendation.")
    private List<GuideTag> recommendedFor;

    @NotBlank(message = "City is required")
    @Size(max = 100, message = "City cannot exceed 100 characters")
    private String city;

    @NotBlank(message = "Country is required")
    @Size(max = 100, message = "Country cannot exceed 100 characters")
    private String country;

    @NotNull(message = "Cost type is required")
    private CostType costType;

    @PositiveOrZero(message = "Price must be positive or zero")
    private BigDecimal price;

    @NotNull(message = "Currency is required")
    private Currency currency;
}
