package ru.ilya.lab2_spring.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@Builder
public class BrandDTO {
    private String id;
    @NotNull
    @NotEmpty
    @Length(min = 2, message = "Brand name must contains minimum two characters")
    private String name;
    private LocalDateTime created;
    private LocalDateTime modified;
}
