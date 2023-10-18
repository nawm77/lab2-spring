package ru.ilya.lab2_spring.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class OfferDTO {
    private UUID id;
    private String description;
    private String engine;
    private String imageUrl;
    private Integer mileage;
    private Integer price;
    private String transmission;
    private Integer year;
    private LocalDateTime created;
    private LocalDateTime modified;
    private ModelDTO modelDTO;
    private SellerDTO sellerDTO;
}
