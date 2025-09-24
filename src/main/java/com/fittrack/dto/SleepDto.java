package com.fittrack.dto;


import lombok.Data;


import java.time.LocalDate;


@Data
public class SleepDto {
    private Long id;
    private LocalDate date;
    private Double hours;
    private Long userId;
}