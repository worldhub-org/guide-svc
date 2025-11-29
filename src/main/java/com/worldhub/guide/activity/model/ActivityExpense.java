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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CostType costType;

    @Column(nullable = false)
    private Currency currency;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "activity_expense_id")
    private List<ActivityExpenseLine> lines;

    @Column(nullable = false)
    private OffsetDateTime createdOn;

    @Column(nullable = false)
    private OffsetDateTime updatedOn;
}
