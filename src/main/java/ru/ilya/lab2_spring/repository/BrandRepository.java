package ru.ilya.lab2_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ilya.lab2_spring.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {
}
