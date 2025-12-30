package com.worldhub.guide.web.dto.section;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SectionCreateRequest {

    @NotNull(message = "Title is required")
    private String title;
    @NotNull(message = "Title is required")
    private String description;
    private Integer orderIndex;
}
