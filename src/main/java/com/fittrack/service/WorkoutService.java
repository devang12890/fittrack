package com.fittrack.service;


import com.fittrack.dto.WorkoutDto;
import com.fittrack.model.User;
import com.fittrack.model.Workout;
import com.fittrack.repository.UserRepository;
import com.fittrack.repository.WorkoutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;


    public WorkoutService(WorkoutRepository workoutRepository, UserRepository userRepository) {
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
    }


    public WorkoutDto create(WorkoutDto dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Workout w = new Workout();
        w.setType(dto.getType());
        w.setDurationMinutes(dto.getDurationMinutes());
        w.setCaloriesBurned(dto.getCaloriesBurned());
        w.setLoggedAt(dto.getLoggedAt() == null ? LocalDateTime.now() : dto.getLoggedAt());
        w.setUser(user);
        Workout saved = workoutRepository.save(w);
        dto.setId(saved.getId());
        return dto;
    }


    public List<WorkoutDto> listByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return workoutRepository.findByUserOrderByLoggedAtDesc(user).stream().map(w -> {
            WorkoutDto d = new WorkoutDto();
            d.setId(w.getId());
            d.setType(w.getType());
            d.setDurationMinutes(w.getDurationMinutes());
            d.setCaloriesBurned(w.getCaloriesBurned());
            d.setLoggedAt(w.getLoggedAt());
            d.setUserId(userId);
            return d;
        }).collect(Collectors.toList());
    }


    public void delete(Long id) {
        workoutRepository.deleteById(id);
    }
}