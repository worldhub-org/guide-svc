package com.worldhub.guide.activity.service;

import com.worldhub.guide.activity.model.ActivityExpense;
import com.worldhub.guide.activity.repository.ActivityExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class ActivityExpenseService {

    private static final Logger log = LoggerFactory.getLogger(ActivityExpenseService.class);

    private final ActivityExpenseRepository activityExpenseRepository;

    public ActivityExpenseService(ActivityExpenseRepository activityExpenseRepository) {
        this.activityExpenseRepository = activityExpenseRepository;
    }

    public ActivityExpense createExpense(UUID activityId) {

        ActivityExpense expense = ActivityExpense.builder()
                .createdOn(OffsetDateTime.now())
                .updatedOn(OffsetDateTime.now())
                .build();

        ActivityExpense persistedExpense = activityExpenseRepository.save(expense);
        log.info("Activity Expense created for Activity with ID {}", activityId);

        return persistedExpense;
    }
}