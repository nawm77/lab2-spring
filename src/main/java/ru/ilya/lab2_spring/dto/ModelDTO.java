package ru.ilya.lab2_spring.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModelDTO {
    private String id;
    @NotNull
    @NotEmpty
    @Length(min = 1, message = "Model name must contains 1 character minimum")
    private String name;
    @NotEmpty
    @NotNull(message = "Category must be chosen")
    @Length(min = 1, message = "Incorrect category")
    private String category;
    private String imageUrl;
    private Integer startYear;
    private Integer endYear;
    private LocalDateTime created;
    private LocalDateTime modified;
    @NotEmpty
    @NotNull
    private BrandDTO brandDTO;
}
