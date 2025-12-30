package com.worldhub.guide.repository;

import com.worldhub.guide.model.Guide;
import com.worldhub.guide.model.GuideStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GuideRepository extends JpaRepository<Guide, UUID> {
    List<Guide> findAllByVersionKey(UUID versionKey);

    // Ordered versions for owner view
    List<Guide> findAllByVersionKeyOrderByVersionDesc(UUID versionKey);

    // Latest published version for public/non-owner views
    Optional<Guide> findTopByVersionKeyAndStatusOrderByVersionDesc(UUID versionKey, GuideStatus status);
}
