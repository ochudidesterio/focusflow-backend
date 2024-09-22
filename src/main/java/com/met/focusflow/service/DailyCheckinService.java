package com.met.focusflow.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.met.focusflow.models.DailyCheckin;

public interface DailyCheckinService  {
    // Creates a new daily check-in entry
    DailyCheckin creatDailyCheckin(DailyCheckin dailyCheckin);
    // Retrieves a daily check-in by its ID, returning an Optional in case the check-in does not exist
    Optional<DailyCheckin>getById(Long id);
    // Finds a single daily check-in for a specific user within a given date range
    Optional<DailyCheckin> findByUserIdAndDateRange(Long userId, LocalDateTime startDate, LocalDateTime endDate);
    // Finds all daily check-ins for a specific user within a given date range
    List<DailyCheckin> findAllByUserIdAndDateRange(Long userId, LocalDateTime startDate, LocalDateTime endDate);

}
