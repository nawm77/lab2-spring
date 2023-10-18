package ru.ilya.lab2_spring.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class BrandDTO {
    private UUID id;
    private String name;
    private LocalDateTime created;
    private LocalDateTime modified;
    private ModelDTO modelDTO;
}
