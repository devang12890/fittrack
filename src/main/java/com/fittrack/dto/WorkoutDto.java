package com.fittrack.dto;


import lombok.Data;


import java.time.LocalDateTime;


@Data
public class WorkoutDto {
    private Long id;
    private String type;
    private Integer durationMinutes;
    private Integer caloriesBurned;
    private LocalDateTime loggedAt;
    private Long userId;
}