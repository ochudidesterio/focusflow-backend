package com.met.focusflow.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.met.focusflow.models.User;
import com.met.focusflow.response.ResponseHandler;
import com.met.focusflow.response.UserDTO;
import com.met.focusflow.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController // Marks this class as a REST controller
@RequiredArgsConstructor // Lombok annotation that generates a constructor for required fields (final fields)
@CrossOrigin // Enables Cross-Origin Resource Sharing for this controller
@RequestMapping("/user") // Maps requests to /user to this controller

public class UserController {
    // Dependency injection of the service for user management
    private final UserService userService;

    @PostMapping("/create") // Maps POST requests to /user/create
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            // Creates a new user and returns a response
            String res = userService.createUser(user);
            return ResponseHandler.response(res, HttpStatus.CREATED, res);
        } catch (Exception e) {
            // Handles any exceptions and returns an error response
            return ResponseHandler.response(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }     
    }

    @GetMapping("/get/{id}") // Maps GET requests to /user/get/{id}
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        try {
            // Retrieves a user by ID and returns it
            User user = userService.getUserById(id).get();
            return ResponseHandler.response("found", HttpStatus.OK, user);
        } catch (Exception e) {
            // Handles any exceptions and returns an error response if user is not found
            return ResponseHandler.response("not found", HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping("/login") // Maps POST requests to /user/login
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            // Attempts to log in the user and returns the response
            UserDTO userDTO = userService.login(user);
            return ResponseHandler.response(userDTO.getMessage(), HttpStatus.OK, userDTO);
        } catch (Exception e) {
            // Handles any exceptions and returns an error response
            return ResponseHandler.response(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}

