package com.met.focusflow.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.met.focusflow.models.DailyCheckin;
import com.met.focusflow.repository.DailyCheckinRepository;

import lombok.RequiredArgsConstructor;

@Service // Marks this class as a Spring service component
@RequiredArgsConstructor // Lombok annotation that generates a constructor with required fields (final fields)

public class DailyCheckinServiceImpl implements DailyCheckinService {

    // Dependency injection of the repository for interacting with the database
    private final DailyCheckinRepository dailyCheckinRepository;

    @Override
    public DailyCheckin creatDailyCheckin(DailyCheckin dailyCheckin) {
        // Sets the current date and time as the timestamp for the check-in
        dailyCheckin.setTimestamp(LocalDateTime.now());

        // Saves the daily check-in to the database and returns the saved entity
        return dailyCheckinRepository.save(dailyCheckin);
    }

    @Override
    public Optional<DailyCheckin> getById(Long id) {
        // Retrieves a daily check-in by its ID, returning an Optional in case it does not exist
        return dailyCheckinRepository.findById(id);
    }

    @Override
    public Optional<DailyCheckin> findByUserIdAndDateRange(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        // Retrieves a single daily check-in for the user within the specified date range
        return dailyCheckinRepository.findByUserIdAndTimestampBetween(userId, startDate, endDate);
    }

    @Override
    public List<DailyCheckin> findAllByUserIdAndDateRange(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        // Retrieves all daily check-ins for the user within the specified date range
        return dailyCheckinRepository.findAllByUserIdAndTimestampBetween(userId, startDate, endDate);
    }
}

