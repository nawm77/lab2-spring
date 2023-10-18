package ru.ilya.lab2_spring.dto;

import lombok.Builder;
import lombok.Data;
import ru.ilya.lab2_spring.entity.enums.Category;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class ModelDTO {
    private UUID id;
    private String name;
    private String category;
    private String imageUrl;
    private Integer startYear;
    private Integer endYear;
    private LocalDateTime created;
    private LocalDateTime modified;
    private BrandDTO brandDTO;
}
