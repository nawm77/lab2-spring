package ru.ilya.lab2_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ilya.lab2_spring.entity.Model;

import java.util.UUID;

@Repository
public interface ModelRepository extends JpaRepository<Model, UUID> {
}
