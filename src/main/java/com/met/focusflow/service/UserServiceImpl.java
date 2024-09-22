package com.met.focusflow.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.met.focusflow.models.User;
import com.met.focusflow.repository.UserRepository;
import com.met.focusflow.response.UserDTO;

import lombok.RequiredArgsConstructor;

@Service // Marks this class as a Spring service component
@RequiredArgsConstructor // Lombok annotation that generates a constructor with required fields (final fields)

public class UserServiceImpl implements UserService {
    
    // Dependency injection of the repository for interacting with the database
    private final UserRepository userRepository;

    @Override
    public String createUser(User user) {
        // Checks if a user with the given email already exists
        if (existsByEmail(user.getEmail())) {
            return "exists"; // Returns "exists" if the email is already taken
        } else {
            // Saves the new user to the database
            userRepository.save(user);
            return "created"; // Returns "created" after successful creation
        }
    }

    @Override
    public Optional<User> getUserById(Long id) {
        // Retrieves a user by their ID, returning an Optional in case the user does not exist
        return userRepository.findById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        // Checks if a user with the specified email already exists in the database
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDTO login(User user) {
        // Checks if the email exists in the database
        if (!existsByEmail(user.getEmail())) {
            return UserDTO.builder() // Returns a DTO with an error message if email not found
                .message("Email not found")
                .build();
        }
        
        // Retrieves the user by email
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());

        // Checks if the user exists
        if (!optionalUser.isPresent()) {
            return UserDTO.builder() // Returns a DTO with an error message if user not found
                .message("User not found")
                .build();
        }

        User u = optionalUser.get(); // Retrieves the user object

        // Checks if the provided password matches the stored password
        if (u.getPassword().equals(user.getPassword())) {
            return UserDTO.builder() // Returns a DTO with user info and success message upon successful login
                .user(u)
                .message("Success")
                .build();
        } else {
            return UserDTO.builder() // Returns a DTO with an error message if the password is incorrect
                .message("Incorrect password")
                .build();
        }
    }
}

