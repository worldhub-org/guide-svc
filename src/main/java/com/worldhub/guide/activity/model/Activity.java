package com.worldhub.guide.activity.model;

import com.worldhub.guide.asset.model.Asset;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Asset> images;

    @Lob
    @Column(nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "activity_id")
    private List<ActivityContact> contacts;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "activity_id")
    private List<ActivityInstruction> instructions;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ActivityExpense expense;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActivityType activityType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DifficultyLevel difficultyLevel;

    private Integer durationValue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DurationUnit durationUnit;

    @Column(nullable = false)
    private OffsetDateTime createdOn;

    @Column(nullable = false)
    private OffsetDateTime updatedOn;
}
