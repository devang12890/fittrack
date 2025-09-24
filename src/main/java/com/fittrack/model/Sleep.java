package com.fittrack.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;


@Entity
@Table(name = "sleeps")
@Getter
@Setter
@NoArgsConstructor
public class Sleep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDate date;
    private Double hours; // e.g., 7.5


    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}