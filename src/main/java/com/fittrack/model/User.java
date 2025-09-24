package com.fittrack.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    private String password;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private Double height;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Meal> meals = new ArrayList<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Workout> workouts = new ArrayList<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Water> waters = new ArrayList<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sleep> sleeps = new ArrayList<>();
}