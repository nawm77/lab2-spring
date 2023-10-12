package ru.ilya.lab2_spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ilya.lab2_spring.entity.enums.Engine;
import ru.ilya.lab2_spring.entity.enums.Transmission;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "offers")
public class Offer {
    @Id
    private Long id;
    private String description;
    private Engine engine;
    private String imageUrl;
    private Integer mileage;
    private Integer price;
    private Transmission transmission;
    private Short year;
    private LocalDateTime created;
    private LocalDateTime modified;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id")
    // может быть тут лучше one to many?
    private Model model;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User seller;
}
