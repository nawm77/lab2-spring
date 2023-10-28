package ru.ilya.lab2_spring.service.impl;

import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilya.lab2_spring.dto.OfferDTO;
import ru.ilya.lab2_spring.mapper.Mapper;
import ru.ilya.lab2_spring.repository.OfferRepository;
import ru.ilya.lab2_spring.service.OfferService;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final Mapper mapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, Mapper mapper) {
        this.offerRepository = offerRepository;
        this.mapper = mapper;
    }

    @Override
    public OfferDTO findById(String id) {
        return mapper.toDTO(offerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such offer with id" + id)));
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public OfferDTO update(OfferDTO object) {
        return null;
    }

    @Override
    public OfferDTO save(OfferDTO object) throws IllegalArgumentRequestException, EntityExistsException {
        return null;
    }

    @Override
    public List<OfferDTO> findAll() {
        return offerRepository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

//    @Override
//    public void addOffer(OfferDTO offer) {
//        offerRepository.save(mapper.toEntity(offer));
//    }
//
//    @Override
//    public void addAll(List<OfferDTO> list) {
//        offerRepository.saveAll(list.stream().map(mapper::toEntity).toList());
//    }
//
//    @Override
//    public void deleteOffer(OfferDTO offer) {
//        offerRepository.delete(mapper.toEntity(offer));
//    }
//
//    @Override
//    public void deleteOfferById(String id) {
//        offerRepository.deleteById(id);
//    }
//
//    @Override
//    public void deleteAll(List<OfferDTO> list) {
//        offerRepository.deleteAll(list.stream().map(mapper::toEntity).toList());
//    }
}