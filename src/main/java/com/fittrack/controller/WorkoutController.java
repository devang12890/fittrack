package com.fittrack.controller;


import com.fittrack.dto.ApiResponse;
import com.fittrack.dto.WorkoutDto;
import com.fittrack.service.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {
    private final WorkoutService workoutService;


    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }


    @PostMapping
    public ResponseEntity<ApiResponse<WorkoutDto>> create(@Valid @RequestBody WorkoutDto dto) {
        WorkoutDto saved = workoutService.create(dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Workout logged", saved));
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<WorkoutDto>>> list(@PathVariable Long userId) {
        return ResponseEntity.ok(new ApiResponse<>(true, "OK", workoutService.listByUser(userId)));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        workoutService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Deleted", null));
    }
}