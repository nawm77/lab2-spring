package ru.ilya.lab2_spring.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BrandDTO {
    private Long id;
    private String name;
    private LocalDateTime created;
    private LocalDateTime modified;
}
