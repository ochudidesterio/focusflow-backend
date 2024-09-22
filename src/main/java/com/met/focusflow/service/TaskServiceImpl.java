package com.met.focusflow.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.met.focusflow.models.Task;
import com.met.focusflow.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service // Marks this class as a Spring service component
@RequiredArgsConstructor // Lombok annotation that generates a constructor with required fields (final fields)

public class TaskServiceImpl implements TaskService {

    // Dependency injection of the repository for interacting with the database
    private final TaskRepository taskRepository;

    @Override
    public Task createTask(Task task) {
        // Sets the current date and time as the timestamp for the task
        task.setTimestamp(LocalDateTime.now());

        // Saves the task to the database and returns the saved entity
        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        // Retrieves a task by its ID, returning an Optional in case it does not exist
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> findAllByUserId(Long userId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        // Retrieves all tasks for a specific user within the specified date and time range
        return taskRepository.findAllByUserIdAndTimestampBetween(userId, startDateTime, endDateTime);
    }
}

