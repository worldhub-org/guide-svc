package com.worldhub.guide.feedback.model;

import com.worldhub.guide.model.Guide;
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
    private UUID userId;
    private Integer rate;
    private String message;
    @ManyToOne
    private Guide guide;
    private OffsetDateTime createdOn;
    private OffsetDateTime updatedOn;
}
