package com.met.focusflow.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.met.focusflow.models.Feedback;
import com.met.focusflow.response.ResponseHandler;
import com.met.focusflow.service.FeedBackService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController // Marks this class as a REST controller
@RequiredArgsConstructor // Lombok annotation that generates a constructor for required fields (final fields)
@CrossOrigin // Enables Cross-Origin Resource Sharing for this controller
@RequestMapping("/feedback") // Maps requests to /feedback to this controller

public class FeedBackController {

    // Dependency injection of the service for feedback management
    private final FeedBackService feedBackService;

    @PostMapping("/create") // Maps POST requests to /feedback/create
    public ResponseEntity<?> createFeedback(@RequestBody Feedback feedback) {
        try {
            // Creates new feedback and returns a success response
            Feedback f = feedBackService.createFeedback(feedback);
            return ResponseHandler.response("created", HttpStatus.CREATED, f);
        } catch (Exception e) {
            // Handles any exceptions and returns an error response
            return ResponseHandler.response(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping("/get/{id}") // Maps GET requests to /feedback/get/{id}
    public ResponseEntity<?> getFeedback(@PathVariable Long id) {
        try {
            // Retrieves feedback by ID and returns it
            Feedback feedback = feedBackService.getById(id).get();
            return ResponseHandler.response("found", HttpStatus.OK, feedback);
        } catch (Exception e) {
            // Handles any exceptions and returns an error response
            return ResponseHandler.response(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}

