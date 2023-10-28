package ru.ilya.lab2_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ilya.lab2_spring.model.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {
    boolean existsById(String id);
}
