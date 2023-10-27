package ru.ilya.lab2_spring.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@Builder
public class OfferDTO {
    private String id;
    private String description;
    @NotNull
    @NotEmpty
    @Length(min = 1, message = "Engine must be chosen")
    private String engine;
    private String imageUrl;
    @NotNull
    private Integer mileage;
    @NotNull
    private Integer price;
    @NotNull
    @NotEmpty
    @Length(min = 1, message = "Transmission must be chosen")
    private String transmission;
    @NotNull
    @NotEmpty
    @Min(value = 1800, message = "Year must be more than 1800")
    private Integer year;
    private LocalDateTime created;
    private LocalDateTime modified;
    @NotNull
    @NotEmpty
    private ModelDTO modelDTO;
    @NotNull
    @NotEmpty
    private UserDTO userDTO;
}
