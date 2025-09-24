package com.fittrack.repository;


import com.fittrack.model.Sleep;
import com.fittrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.util.List;


public interface SleepRepository extends JpaRepository<Sleep, Long> {
    List<Sleep> findByUserOrderByDateDesc(User user);
    List<Sleep> findByUserAndDateBetween(User user, LocalDate start, LocalDate end);
}