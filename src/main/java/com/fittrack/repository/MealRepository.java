package com.fittrack.repository;


import com.fittrack.model.Meal;
import com.fittrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDateTime;
import java.util.List;


public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByUserOrderByLoggedAtDesc(User user);
    List<Meal> findByUserAndLoggedAtBetween(User user, LocalDateTime start, LocalDateTime end);
}