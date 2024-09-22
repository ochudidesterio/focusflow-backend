package com.met.focusflow.models;

import java.time.LocalDateTime;
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
@Entity(name ="tbl_dailycheckins")
public class DailyCheckin {

     // Marks the 'id' field as the primary key and specifies how the ID value will be generated
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO) // Automatically generates ID values
     private Long id; // Unique identifier for each DailyCheckin entry
 
     // Fields representing various aspects of a daily check-in
     private int mood;      // Stores the mood rating for the day 
     private int energy;    // Stores the energy level rating for the day 
     private int ambition;  // Stores the ambition or motivation level rating 
     private int wellbeing; // Stores the overall well-being rating 
 
     // Timestamp to record when the check-in was created
     private LocalDateTime timestamp; // Stores the date and time of the check-in
 
     // User ID to link this check-in to a specific user
     private Long userId; // Stores the ID of the user who made the check-in
    
}
