package com.met.focusflow.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.met.focusflow.models.Task;

public interface TaskService {

    // Creates a new task entry
    Task createTask(Task task);

    // Retrieves a task by its ID, returning an Optional in case the task does not exist
    Optional<Task> getTaskById(Long id);

    // Finds all tasks for a specific user within a given date and time range
    List<Task> findAllByUserId(Long userId, LocalDateTime startDateTime, LocalDateTime endDateTime);
    
}
