package com.fittrack.controller;


import com.fittrack.dto.ApiResponse;
import com.fittrack.dto.SleepDto;
import com.fittrack.service.SleepService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/sleep")
public class SleepController {
    private final SleepService sleepService;


    public SleepController(SleepService sleepService) {
        this.sleepService = sleepService;
    }


    @PostMapping
    public ResponseEntity<ApiResponse<SleepDto>> create(@Valid @RequestBody SleepDto dto) {
        SleepDto saved = sleepService.create(dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Sleep logged", saved));
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<SleepDto>>> list(@PathVariable Long userId) {
        return ResponseEntity.ok(new ApiResponse<>(true, "OK", sleepService.listByUser(userId)));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        sleepService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Deleted", null));
    }
}