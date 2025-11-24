package com.worldhub.guide.activity.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityExpenseLine {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private BigDecimal amount;
    private String notes;
    @Column(nullable = false)
    private OffsetDateTime createdOn;
    @Column(nullable = false)
    private OffsetDateTime updatedOn;
}
