package com.fittrack.repository;


import com.fittrack.model.User;
import com.fittrack.model.Water;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDateTime;
import java.util.List;


public interface WaterRepository extends JpaRepository<Water, Long> {
    List<Water> findByUserOrderByLoggedAtDesc(User user);
    List<Water> findByUserAndLoggedAtBetween(User user, LocalDateTime start, LocalDateTime end);
}