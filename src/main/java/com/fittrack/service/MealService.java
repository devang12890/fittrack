package com.fittrack.service;


import com.fittrack.dto.MealDto;
import com.fittrack.model.Meal;
import com.fittrack.model.User;
import com.fittrack.repository.MealRepository;
import com.fittrack.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class MealService {
    private final MealRepository mealRepository;
    private final UserRepository userRepository;


    public MealService(MealRepository mealRepository, UserRepository userRepository) {
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
    }


    public MealDto create(MealDto dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Meal m = new Meal();
        m.setName(dto.getName());
        m.setCalories(dto.getCalories());
        m.setProteins(dto.getProteins());
        m.setCarbs(dto.getCarbs());
        m.setFats(dto.getFats());
        m.setLoggedAt(dto.getLoggedAt() == null ? LocalDateTime.now() : dto.getLoggedAt());
        m.setUser(user);
        Meal saved = mealRepository.save(m);
        dto.setId(saved.getId());
        return dto;
    }


    public List<MealDto> listByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return mealRepository.findByUserOrderByLoggedAtDesc(user).stream().map(m -> {
            MealDto d = new MealDto();
            d.setId(m.getId());
            d.setName(m.getName());
            d.setCalories(m.getCalories());
            d.setProteins(m.getProteins());
            d.setCarbs(m.getCarbs());
            d.setFats(m.getFats());
            d.setLoggedAt(m.getLoggedAt());
            d.setUserId(userId);
            return d;
        }).collect(Collectors.toList());
    }


    public void delete(Long id) {
        mealRepository.deleteById(id);
    }
}