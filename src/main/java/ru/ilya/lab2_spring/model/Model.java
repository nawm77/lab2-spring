package ru.ilya.lab2_spring.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.ilya.lab2_spring.converter.CategoryConverter;
import ru.ilya.lab2_spring.model.enums.Category;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Model extends BaseEntity {
    @Convert(converter = CategoryConverter.class)
    private Category category;
    @Column(nullable = false, unique = true)
    private String name;
    private String imageUrl;
    private Integer startYear;
    private Integer endYear;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @OneToMany(mappedBy = "model", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Offer> offerList;
}
