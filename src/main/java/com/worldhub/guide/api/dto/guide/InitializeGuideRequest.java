package com.worldhub.guide.api.dto.guide;

import com.worldhub.guide.model.CostType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record InitializeGuideRequest(

        @NotBlank
        @Size(min = 6, max = 32)
        String title,

        @NotBlank
        @Size(min = 32, max = 256)
        String shortDescription,

        @NotBlank
        @Size(min = 2, max = 16)
        String destination,

        @NotBlank
        @Size(min = 2, max = 16)
        String country,

        @NotNull
        CostType costType
) { }