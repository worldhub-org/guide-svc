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
    private String title;

    private String description;

    @Column(nullable = false)
    private String placeName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private ActivityType type;

    private Double latitude;

    private Double longitude;

    private DurationUnit durationUnit;

    private Double durationValue;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DayPart bestTimeToDo;

    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficultyLevel;

    private Integer minPeopleRequired;

    private Integer minAge;

    @OneToOne(optional = false)
    private ActivityExpense expense;

    private String contactPhone;

    private String contactEmail;

    private String officialWebsite;

    @Column(nullable = false)
    private OffsetDateTime createdOn;

    @Column(nullable = false)
    private OffsetDateTime updatedOn;
}
