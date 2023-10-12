package ru.ilya.lab2_spring.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class ModelDTO {
    private Long id;
    private String name;
    private String category;
    private String imageUrl;
    private Date startYear;
    private Date endYear;
    private LocalDateTime created;
    private LocalDateTime modified;
    private String brandName;
    private LocalDateTime brandCreated;
    private LocalDateTime brandModified;
}
