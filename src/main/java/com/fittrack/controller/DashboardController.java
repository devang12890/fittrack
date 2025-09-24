package com.fittrack.controller;


import com.fittrack.dto.ApiResponse;
import com.fittrack.model.User;
import com.fittrack.repository.MealRepository;
import com.fittrack.repository.WaterRepository;
import com.fittrack.repository.WorkoutRepository;
import com.fittrack.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {


    private final UserService userService;
    private final MealRepository mealRepository;
    private final WorkoutRepository workoutRepository;
    private final WaterRepository waterRepository;


    public DashboardController(UserService userService, MealRepository mealRepository, WorkoutRepository workoutRepository, WaterRepository waterRepository) {
        this.userService = userService;
        this.mealRepository = mealRepository;
        this.workoutRepository = workoutRepository;
        this.waterRepository = waterRepository;
    }


    // weekly summary for a user between two dates (inclusive)
    @GetMapping("/summary/{userId}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> weeklySummary(
            @PathVariable Long userId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        User user = userService.findById(userId);
        LocalDate startDate = from == null ? LocalDate.now().minusWeeks(1) : from;
        LocalDate endDate = to == null ? LocalDate.now() : to;


        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(LocalTime.MAX);


        int totalCaloriesConsumed = mealRepository.findByUserAndLoggedAtBetween(user, start, end).stream().mapToInt(m -> m.getCalories() == null ? 0 : m.getCalories()).sum();
        int totalCaloriesBurned = workoutRepository.findByUserAndLoggedAtBetween(user, start, end).stream().mapToInt(w -> w.getCaloriesBurned() == null ? 0 : w.getCaloriesBurned()).sum();
        int totalWaterMl = waterRepository.findByUserAndLoggedAtBetween(user, start, end).stream().mapToInt(w -> w.getMl() == null ? 0 : w.getMl()).sum();


        Map<String, Object> map = new HashMap<>();
        map.put("caloriesConsumed", totalCaloriesConsumed);
        map.put("caloriesBurned", totalCaloriesBurned);
        map.put("waterMl", totalWaterMl);


        return ResponseEntity.ok(new ApiResponse<>(true, "OK", map));
    }
}