package com.worldhub.guide.repository;

import com.worldhub.guide.model.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GuideRepository extends JpaRepository<Guide, UUID> {
}
