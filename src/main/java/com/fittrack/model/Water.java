package com.fittrack.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;


@Entity
@Table(name = "waters")
@Getter
@Setter
@NoArgsConstructor
public class Water {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Integer ml; // milliliters
    private LocalDateTime loggedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}