package ru.ilya.lab2_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ilya.lab2_spring.model.Model;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, String> {
    List<Model> findAllByName(String name);
}
