package com.worldhub.guide.activity.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityContact {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ContactType contactType;

    @Column(nullable = false)
    private String contactValue;

    private String address;

    private Double latitude;
    private Double longitude;
}
