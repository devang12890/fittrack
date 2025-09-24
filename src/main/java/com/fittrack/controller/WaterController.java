package com.fittrack.controller;


import com.fittrack.dto.ApiResponse;
import com.fittrack.dto.WaterDto;
import com.fittrack.service.WaterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/water")
public class WaterController {
    private final WaterService waterService;


    public WaterController(WaterService waterService) {
        this.waterService = waterService;
    }


    @PostMapping
    public ResponseEntity<ApiResponse<WaterDto>> create(@Valid @RequestBody WaterDto dto) {
        WaterDto saved = waterService.create(dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Water logged", saved));
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<WaterDto>>> list(@PathVariable Long userId) {
        return ResponseEntity.ok(new ApiResponse<>(true, "OK", waterService.listByUser(userId)));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        waterService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Deleted", null));
    }
}