package com.met.focusflow.service;

import java.util.Optional;

import com.met.focusflow.models.User;
import com.met.focusflow.response.UserDTO;

public interface UserService {

    // Creates a new user and returns a message or identifier (e.g., user ID)
    String createUser(User user);

    // Retrieves a user by their ID, returning an Optional in case the user does not exist
    Optional<User> getUserById(Long id);

    // Checks if a user with the specified email already exists
    boolean existsByEmail(String email);

    // Authenticates a user and returns a UserDTO containing user information upon successful login
    UserDTO login(User user);

    
}
