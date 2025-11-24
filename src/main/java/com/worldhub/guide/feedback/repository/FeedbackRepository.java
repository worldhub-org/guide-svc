package com.worldhub.guide.feedback.repository;

import com.worldhub.guide.feedback.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FeedbackRepository extends JpaRepository<Review, UUID> {
}
