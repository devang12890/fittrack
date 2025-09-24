package com.fittrack.controller;


import com.fittrack.dto.ApiResponse;
import com.fittrack.dto.MealDto;
import com.fittrack.service.MealService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/meals")
public class MealController {
    private final MealService mealService;


    public MealController(MealService mealService) {
        this.mealService = mealService;
    }


    @PostMapping
    public ResponseEntity<ApiResponse<MealDto>> create(@Valid @RequestBody MealDto dto) {
        MealDto saved = mealService.create(dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Meal logged", saved));
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<MealDto>>> list(@PathVariable Long userId) {
        return ResponseEntity.ok(new ApiResponse<>(true, "OK", mealService.listByUser(userId)));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        mealService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Deleted", null));
    }
}