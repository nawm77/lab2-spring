package ru.ilya.lab2_spring.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import ru.ilya.lab2_spring.entity.enums.Engine;
import ru.ilya.lab2_spring.entity.enums.Transmission;

import java.time.LocalDateTime;

@Data
@Builder
public class OfferDTO {
    private Long id;
    private String description;
    @Enumerated(EnumType.STRING)
    private Engine engine;
    private String imageUrl;
    private Integer mileage;
    private Integer price;
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    private Short year;
    private LocalDateTime created;
    private LocalDateTime modified;
    private ModelDTO modelDTO;
    private SellerDTO sellerDTO;
}
