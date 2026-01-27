package com.worldhub.guide.section.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.worldhub.guide.model.Guide;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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

    @ManyToOne(optional = false)
    private Guide guide;

    private String title;

    private String description;

    private int orderIndex;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @OrderBy("orderIndex ASC")
    private List<SectionActivity> activities = new ArrayList<>();
}