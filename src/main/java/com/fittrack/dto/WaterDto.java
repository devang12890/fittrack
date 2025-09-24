package com.fittrack.dto;


import lombok.Data;


import java.time.LocalDateTime;


@Data
public class WaterDto {
    private Long id;
    private Integer ml;
    private LocalDateTime loggedAt;
    private Long userId;
}