package ru.ilya.lab2_spring.model;

import jakarta.persistence.*;
import lombok.*;
import ru.ilya.lab2_spring.model.enums.Engine;
import ru.ilya.lab2_spring.model.enums.Transmission;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Offer extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    @Enumerated(EnumType.STRING)
    private Engine engine;

    @Column(unique = true)
    private String imageUrl;
    private String description;
    private Integer mileage;
    private Integer price;
    private Integer year;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id")
    private User seller;
}
