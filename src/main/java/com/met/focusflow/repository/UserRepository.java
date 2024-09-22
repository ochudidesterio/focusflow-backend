package com.met.focusflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.met.focusflow.models.User;
import java.util.Optional;




public interface UserRepository extends JpaRepository<User,Long> {
   // Checks if a user with the specified email already exists in the database
   boolean existsByEmail(String email);

   // Finds a user by their email, returning an Optional in case the user does not exist
   Optional<User> findByEmail(String email);
}
