package com.met.focusflow.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.met.focusflow.models.DailyCheckin;
import com.met.focusflow.response.ResponseHandler;
import com.met.focusflow.service.DailyCheckinService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RequiredArgsConstructor // Lombok annotation that generates a constructor for required fields (final fields)
@CrossOrigin // Enables Cross-Origin Resource Sharing for this controller
@RestController // Marks this class as a REST controller
@RequestMapping("/checkins") // Maps requests to /checkins to this controller

public class DailyCheckinController {

    // Dependency injection of the service for daily check-ins
    private final DailyCheckinService dailyCheckinService;

    @PostMapping("/create") // Maps POST requests to /checkins/create
    public ResponseEntity<?> create(@RequestBody DailyCheckin dailyCheckin) {
        try {
            // Creates a new daily check-in and returns a success response
            DailyCheckin checkin = dailyCheckinService.creatDailyCheckin(dailyCheckin);
            return ResponseHandler.response("created", HttpStatus.CREATED, checkin);
        } catch (Exception e) {
            // Handles any exceptions and returns an error response
            return ResponseHandler.response(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping("/get/{id}") // Maps GET requests to /checkins/get/{id}
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            // Retrieves a daily check-in by ID and returns it
            DailyCheckin dailyCheckin = dailyCheckinService.getById(id).get();
            return ResponseHandler.response("found", HttpStatus.OK, dailyCheckin);
        } catch (Exception e) {
            // Handles any exceptions and returns an error response
            return ResponseHandler.response(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping("/{userId}/start/{startDate}/end/{endDate}") // Maps GET requests for a range of check-ins
    public ResponseEntity<?> dailyCheckinRange(@PathVariable Long userId, @PathVariable String startDate, @PathVariable String endDate) {
        try {
            // Parses start and end dates from the path variables
            LocalDate startLocalDate = LocalDate.parse(startDate); 
            LocalDate endLocalDate = LocalDate.parse(endDate); 

            // Sets the time to the start and end of the specified days
            LocalDateTime startOfDay = startLocalDate.atStartOfDay();
            LocalDateTime endOfDay = endLocalDate.atTime(23, 59, 59);

            // Retrieves all daily check-ins for the specified user and date range
            List<DailyCheckin> checkin = dailyCheckinService.findAllByUserIdAndDateRange(userId, startOfDay, endOfDay);
            return ResponseHandler.response("found", HttpStatus.OK, checkin);
        } catch (Exception e) {
            // Handles any exceptions and returns an error response
            return ResponseHandler.response("not found", HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping("/{userId}/date/{date}") // Maps GET requests for a specific day's check-ins
    public ResponseEntity<?> dailyCheckin(@PathVariable Long userId, @PathVariable String date) {
        try {
            // Parses the date from the path variable
            LocalDate localDate = LocalDate.parse(date); 

            // Sets the time to the start and end of the specified day
            LocalDateTime startOfDay = localDate.atStartOfDay();
            LocalDateTime endOfDay = localDate.atTime(23, 59, 59);

            // Retrieves a daily check-in for the specified user and date
            DailyCheckin checkin = dailyCheckinService.findByUserIdAndDateRange(userId, startOfDay, endOfDay).get();
            return ResponseHandler.response("found", HttpStatus.OK, checkin);
        } catch (Exception e) {
            // Handles any exceptions and returns an error response
            return ResponseHandler.response("not found", HttpStatus.MULTI_STATUS, null);
        }
    }
}
