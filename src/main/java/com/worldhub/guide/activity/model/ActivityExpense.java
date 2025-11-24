package com.worldhub.guide.activity.model;

import com.worldhub.guide.model.CostType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private CostType costType;
    private Currency currency;
    private BigDecimal totalAmount;
    @OneToMany(fetch = FetchType.EAGER)
    private List<ActivityExpenseLine> lines;
    @Column(nullable = false)
    private OffsetDateTime createdOn;
    @Column(nullable = false)
    private OffsetDateTime updatedOn;
}
