package com.worldhub.guide.activity.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private UUID userId;
    @Column(nullable = false)
    private String name;
    private String imageUrl;
    private String description;
    @OneToOne(optional = false)
    private ActivityExpense expense;
    @Column(nullable = false)
    private ActivityType activityType;
    @Column(nullable = false)
    private DifficultyLevel difficultyLevel;
    private Integer durationValue;
    @Column(nullable = false)
    private DurationUnit durationUnit;
    @Column(nullable = false)
    private OffsetDateTime createdOn;
    @Column(nullable = false)
    private OffsetDateTime updatedOn;
}
