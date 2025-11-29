package com.worldhub.guide.web.dto.activity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityExpenseLineResponse {

    private UUID id;
    private String title;
    private BigDecimal amount;
    private String notes;
    private OffsetDateTime createdOn;
    private OffsetDateTime updatedOn;
}
