package ru.ilya.lab2_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ilya.lab2_spring.model.Brand;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {
    List<Brand> findAllByName(String name);
}
