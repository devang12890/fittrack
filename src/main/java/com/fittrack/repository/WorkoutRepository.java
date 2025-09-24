package com.fittrack.repository;


import com.fittrack.model.User;
import com.fittrack.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDateTime;
import java.util.List;


public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByUserOrderByLoggedAtDesc(User user);
    List<Workout> findByUserAndLoggedAtBetween(User user, LocalDateTime start, LocalDateTime end);
}