package com.worldhub.guide.model;

import com.worldhub.guide.section.model.Section;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private UUID ownerId;
    @Column(nullable = false)
    private String ownerEmail;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String shortDescription;
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GuideStatus status;
    private Integer version;
    @Column(nullable = false)
    private String destination;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private CostType costType;
    private BigDecimal price;
    private Currency currency;
    private Double averageRate;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderIndex ASC")
    private List<Section> sections = new ArrayList<>();
    @Column(nullable = false)
    private OffsetDateTime createdOn;
    @Column(nullable = false)
    private OffsetDateTime updatedOn;
}
