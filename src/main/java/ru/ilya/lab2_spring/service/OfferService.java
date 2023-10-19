package ru.ilya.lab2_spring.service;

import ru.ilya.lab2_spring.dto.OfferDTO;
import ru.ilya.lab2_spring.entity.Offer;

import java.util.List;
import java.util.UUID;

public interface OfferService {
    List<OfferDTO> findAll();
    OfferDTO findById(UUID id);
    void addOffer(OfferDTO offer);
    void addAll(List<OfferDTO> list);
    void deleteOffer(OfferDTO offer);
    void deleteOfferById(UUID id);
    void deleteAll(List<OfferDTO> list);
}
