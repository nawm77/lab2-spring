package ru.ilya.lab2_spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ilya.lab2_spring.entity.enums.Category;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "models")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Category category;
    private String imageUrl;
    private Date startYear;
    private Date endYear;
    private LocalDateTime created;
    private LocalDateTime modified;
    @ManyToOne(targetEntity = Brand.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private Brand brand;
}
