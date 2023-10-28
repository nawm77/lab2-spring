package ru.ilya.lab2_spring.service;

import ru.ilya.lab2_spring.dto.OfferDTO;

import java.util.List;

public interface OfferService extends BaseService<OfferDTO> {
    List<OfferDTO> findAll();

    OfferDTO findById(String id);
}
