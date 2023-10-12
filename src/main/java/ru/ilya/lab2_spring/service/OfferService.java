package ru.ilya.lab2_spring.service;

import ru.ilya.lab2_spring.entity.Offer;

import java.util.List;

public interface OfferService {
    List<Offer> findAll();

}
