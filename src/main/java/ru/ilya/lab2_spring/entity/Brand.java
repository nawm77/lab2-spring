package ru.ilya.lab2_spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private LocalDateTime created;
    private LocalDateTime modified;
    @OneToMany(mappedBy = "brand")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "brand_id")
    private Set<Model> model;
}
