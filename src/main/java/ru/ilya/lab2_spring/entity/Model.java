package ru.ilya.lab2_spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.ilya.lab2_spring.converter.CategoryConverter;
import ru.ilya.lab2_spring.entity.enums.Category;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Convert(converter = CategoryConverter.class)
    private Category category;

    private String name;
    private String imageUrl;
    private Integer startYear;
    private Integer endYear;
    private LocalDateTime created;
    private LocalDateTime modified;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @OneToMany(mappedBy = "model", cascade = {CascadeType.ALL, CascadeType.ALL})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Offer> offerList;
}
