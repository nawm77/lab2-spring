package ru.ilya.lab2_spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ilya.lab2_spring.entity.enums.Engine;
import ru.ilya.lab2_spring.entity.enums.Transmission;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    @Enumerated(EnumType.STRING)
    private Engine engine;

    private String imageUrl;
    private String description;
    private Integer mileage;
    private Integer price;
    private Integer year;
    private LocalDateTime created;
    private LocalDateTime modified;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "model_id")
    private Model model;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "seller_id")
    private User seller;
}
