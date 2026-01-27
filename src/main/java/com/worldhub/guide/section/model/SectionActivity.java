package com.worldhub.guide.section.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.worldhub.guide.activity.model.Activity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectionActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    @JsonBackReference
    private Section section;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Activity activity;

    private int orderIndex;
}
