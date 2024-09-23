package com.met.focusflow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity(name = "tbl_user") 
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Long id; // Unique identifier for each user
    private String name; // Stores the full name of the user
    private String email; // Stores the user's email address
    private String username; // Stores the username 
    private String password; // Stores the user's password 
}
