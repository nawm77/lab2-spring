package ru.ilya.lab2_spring.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid-string")
    @GenericGenerator(name = "uuid-string",
                    strategy = "org.hibernate.id.UUI" +
                            "DGenerator")
    @Column(unique = true)
    private String id;
    @Column(nullable = false)
    private LocalDateTime created;
    private LocalDateTime modified;

    @PrePersist
    private void create(){
        created=LocalDateTime.now();
    }

    @PreUpdate
    private void update(){
        modified = LocalDateTime.now();
    }
}
