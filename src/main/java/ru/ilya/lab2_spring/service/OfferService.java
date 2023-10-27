package ru.ilya.lab2_spring.service;

import ru.ilya.lab2_spring.dto.OfferDTO;

import java.util.List;

public interface OfferService extends BaseService<OfferDTO> {
    List<OfferDTO> findAll();
    OfferDTO findById(String id);
    void addOffer(OfferDTO offer);
    void addAll(List<OfferDTO> list);
    void deleteOffer(OfferDTO offer);
    void deleteOfferById(String id);
    void deleteAll(List<OfferDTO> list);
}
