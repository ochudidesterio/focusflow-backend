package com.met.focusflow.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
@Entity(name = "tbl_task") // Marks this class as a JPA entity with the table name "tbl_task"
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Specifies that the ID should be automatically generated
    private Long id; // Unique identifier for each task entry

    private int timespent; // Stores the time spent on the task 

    @Column(nullable = false) // Ensures that the userId cannot be null in the database
    private Long userId; // Stores the ID of the user associated with the task

    private LocalDateTime timestamp; // Stores the date and time when the task was created or logged
}
