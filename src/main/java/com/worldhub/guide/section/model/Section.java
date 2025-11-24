package com.worldhub.guide.section.model;

import com.worldhub.guide.activity.model.Activity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String title;
    private String description;
    private String imageUrl;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Activity> activities;
}
