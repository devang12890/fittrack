package com.fittrack.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;


@Entity
@Table(name = "workouts")
@Getter
@Setter
@NoArgsConstructor
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String type; // e.g., Running, Strength
    private Integer durationMinutes; // duration in minutes
    private Integer caloriesBurned;
    private LocalDateTime loggedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}