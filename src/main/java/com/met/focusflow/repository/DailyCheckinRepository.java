package com.met.focusflow.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.met.focusflow.models.DailyCheckin;

public interface DailyCheckinRepository extends JpaRepository<DailyCheckin, Long> {
    
    // Finds a single daily check-in for a user within a specific date and time range
    Optional<DailyCheckin> findByUserIdAndTimestampBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);

    // Finds all daily check-ins for a user within a specific date and time range
    List<DailyCheckin> findAllByUserIdAndTimestampBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);
}
