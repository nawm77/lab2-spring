package ru.ilya.lab2_spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilya.lab2_spring.entity.Offer;
import ru.ilya.lab2_spring.repository.OfferRepository;
import ru.ilya.lab2_spring.service.OfferService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public Offer findById(UUID id) {
        return offerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such offer with id" + id));
    }

    @Override
    public List<Offer> findAll() {
        return offerRepository.findAll();
    }

    @Override
    public void addOffer(Offer offer) {
        offerRepository.save(offer);
    }

    @Override
    public void addAll(List<Offer> list) {
        offerRepository.saveAll(list);
    }

    @Override
    public void deleteOffer(Offer offer) {
        offerRepository.delete(offer);
    }

    @Override
    public void deleteOfferById(UUID id) {
        offerRepository.deleteById(id);
    }

    @Override
    public void deleteAll(List<Offer> list) {
        offerRepository.deleteAll(list);
    }
}