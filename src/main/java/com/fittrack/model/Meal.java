package com.fittrack.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;


@Entity
@Table(name = "meals")
@Getter
@Setter
@NoArgsConstructor
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name; // ex: Breakfast
    private Integer calories; // kcal
    private Integer proteins; // grams (optional)
    private Integer carbs;
    private Integer fats;


    private LocalDateTime loggedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}