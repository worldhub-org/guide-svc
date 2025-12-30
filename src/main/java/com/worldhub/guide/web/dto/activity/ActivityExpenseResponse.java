package com.worldhub.guide.web.dto.activity;

import com.worldhub.guide.model.CostType;
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
public class ActivityExpenseResponse {

    private UUID id;
    private CostType costType;
    private Currency currency;
    private BigDecimal totalAmount;
    private List<ActivityExpenseLineResponse> lines;
    private OffsetDateTime createdOn;
    private OffsetDateTime updatedOn;
}
