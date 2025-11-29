package com.worldhub.guide.model;

import com.worldhub.guide.feedback.model.Review;
import com.worldhub.guide.section.model.Section;
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
public class Guide {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID ownerId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<GuideTag> recommendedFor;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CostType costType;

    private BigDecimal price;

    private Currency currency;

    private Double averageRate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "guide_id")
    private List<Section> sections;

    @Column(nullable = false)
    private double version;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GuideStatus status;

    private boolean isDeleted;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "guide_id")
    private List<Review> reviews;

    @Column(nullable = false)
    private OffsetDateTime createdOn;

    @Column(nullable = false)
    private OffsetDateTime updatedOn;
}
