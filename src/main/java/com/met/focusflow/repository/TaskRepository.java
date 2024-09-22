package com.met.focusflow.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.met.focusflow.models.Task;

public interface TaskRepository extends JpaRepository<Task,Long> {

    // Finds all tasks for a specific user within a given date and time range
    List<Task> findAllByUserIdAndTimestampBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);
    
}
