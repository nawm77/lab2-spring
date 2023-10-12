package ru.ilya.lab2_spring.service;

import ru.ilya.lab2_spring.entity.Offer;

import java.util.List;

public interface OfferService {
    List<Offer> findAll();
    Offer findById(Long id);
    void addOffer(Offer offer);
    void addAll(List<Offer> list);
    void deleteOffer(Offer offer);
    void deleteOfferById(Long id);
    void deleteAll(List<Offer> list);
}
