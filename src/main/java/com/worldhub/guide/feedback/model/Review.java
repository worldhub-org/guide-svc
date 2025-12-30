package com.worldhub.guide.feedback.model;

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
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    private Integer rate;

    private String message;

    @Column(nullable = false)
    private OffsetDateTime createdOn;

    @Column(nullable = false)
    private OffsetDateTime updatedOn;
}
