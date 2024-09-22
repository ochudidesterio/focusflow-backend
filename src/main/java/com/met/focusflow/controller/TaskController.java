package com.met.focusflow.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.met.focusflow.models.Task;
import com.met.focusflow.response.ResponseHandler;
import com.met.focusflow.service.TaskService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController // Marks this class as a REST controller
@RequiredArgsConstructor // Lombok annotation that generates a constructor for required fields (final fields)
@CrossOrigin // Enables Cross-Origin Resource Sharing for this controller
@RequestMapping("/task") // Maps requests to /task to this controller

public class TaskController {

    // Dependency injection of the service for task management
    private final TaskService taskService;

    @PostMapping("/create") // Maps POST requests to /task/create
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        try {
            // Creates a new task and returns a success response
            Task t = taskService.createTask(task);
            return ResponseHandler.response("saved", HttpStatus.CREATED, t); 
        } catch (Exception e) {
            // Handles any exceptions and returns an error response
            return ResponseHandler.response(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping("/get/{id}") // Maps GET requests to /task/get/{id}
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            // Retrieves a task by ID and returns it
            Task task = taskService.getTaskById(id).get();
            return ResponseHandler.response("found", HttpStatus.OK, task);
        } catch (Exception e) {
            // Handles any exceptions and returns an error response
            return ResponseHandler.response(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping("/user/{id}/start/{startDate}/end/{endDate}") // Maps GET requests to retrieve tasks for a user in a date range
    public ResponseEntity<?> findAllByUserid(@PathVariable Long id, @PathVariable String startDate, @PathVariable String endDate) {
        try {
            // Parses start and end dates from the path variables
            LocalDate startLocalDate = LocalDate.parse(startDate);
            LocalDate endLocalDate = LocalDate.parse(endDate);

            List<Map<String, Object>> results = new ArrayList<>(); // List to hold daily task data
            
            // Loops through each date in the range
            for (LocalDate date = startLocalDate; !date.isAfter(endLocalDate); date = date.plusDays(1)) {
                LocalDateTime startOfDay = date.atStartOfDay(); // Start of the day
                LocalDateTime endOfDay = date.atTime(23, 59, 59); // End of the day

                // Retrieves tasks for the user on the specific date
                List<Task> tasks = taskService.findAllByUserId(id, startOfDay, endOfDay);

                // Calculates total time spent and number of tasks
                long totalTimeSpent = tasks.stream()
                                            .mapToLong(Task::getTimespent) // In seconds
                                            .sum();
                
                int totalTasks = tasks.size(); // Count of tasks

                // Creates a map for daily data and adds it to results
                Map<String, Object> dailyData = new HashMap<>();
                dailyData.put("date", date.toString());
                dailyData.put("totalTimeSpent", totalTimeSpent / 60); // Convert to minutes
                dailyData.put("totalTasks", totalTasks);
                
                results.add(dailyData);
            }

            // Returns the collected results
            return ResponseHandler.response("found", HttpStatus.OK, results);
        } catch (Exception e) {
            // Handles any exceptions and returns an error response
            return ResponseHandler.response(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}

