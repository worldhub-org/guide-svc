package com.worldhub.guide.activity.repository;

import com.worldhub.guide.activity.model.ActivityExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ActivityExpenseRepository extends JpaRepository<ActivityExpense, UUID> {
}
