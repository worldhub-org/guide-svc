package com.worldhub.guide.purchase.repository;

import com.worldhub.guide.purchase.model.PurchasedGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PurchasedGuideRepository extends JpaRepository<PurchasedGuide, UUID> {
}
