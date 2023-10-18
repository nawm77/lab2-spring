package ru.ilya.lab2_spring.service;

import ru.ilya.lab2_spring.entity.Offer;

import java.util.List;
import java.util.UUID;

public interface OfferService {
    List<Offer> findAll();
    Offer findById(UUID id);
    void addOffer(Offer offer);
    void addAll(List<Offer> list);
    void deleteOffer(Offer offer);
    void deleteOfferById(UUID id);
    void deleteAll(List<Offer> list);
}
