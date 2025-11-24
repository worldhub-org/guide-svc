package com.worldhub.guide.model;

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
    private String description;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private CostType costType;
    private BigDecimal price;
    private Currency currency;
    private Double averageRate;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Section> sections;
    @Column(nullable = false)
    private OffsetDateTime createdOn;
    @Column(nullable = false)
    private OffsetDateTime updatedOn;
}
