package com.worldhub.guide.purchase.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchasedGuide {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private UUID guideId;

    // Product-level identity that stays the same across versions of the same guide
    @Column(nullable = false)
    private UUID versionKey;

    @Column(nullable = false)
    private UUID paymentReference;
}
