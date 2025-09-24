package com.fittrack.dto;


import lombok.Data;


import java.time.LocalDateTime;


@Data
public class MealDto {
    private Long id;
    private String name;
    private Integer calories;
    private Integer proteins;
    private Integer carbs;
    private Integer fats;
    private LocalDateTime loggedAt;
    private Long userId;
}