package ru.ilya.lab2_spring.service.impl;

import jakarta.persistence.EntityExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.ilya.lab2_spring.dto.OfferDTO;
import ru.ilya.lab2_spring.mapper.Mapper;
import ru.ilya.lab2_spring.repository.OfferRepository;
import ru.ilya.lab2_spring.service.OfferService;
import ru.ilya.lab2_spring.service.util.ValidationUtil;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final Mapper mapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, Mapper mapper, ValidationUtil validationUtil) {
        this.offerRepository = offerRepository;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public OfferDTO findById(String id) {
        return mapper.toDTO(offerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such offer with id" + id)));
    }

    @Override
    public Boolean existsById(String id) {
        return offerRepository.existsById(id);
    }

    @Override
    public void deleteById(String id) {
        try {
            offerRepository.deleteById(id);
            log.info("Delete brand with id {}", id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getLocalizedMessage());
        }
    }

    @Override
    public OfferDTO update(OfferDTO object) throws IllegalArgumentRequestException {
        if (offerRepository.findById(object.getId()).isPresent()) {
            log.info("Update offer {}", object);
        }
        return saveOrUpdate(object);
    }

    @Override
    public OfferDTO save(OfferDTO object) throws IllegalArgumentRequestException, EntityExistsException {
        OfferDTO dto = saveOrUpdate(object);
        log.info("Create offer {} with id {}", dto, dto.getId());
        return dto;
    }

    @Override
    public List<OfferDTO> findAll() {
        return offerRepository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    private OfferDTO saveOrUpdate(OfferDTO offerDTO) throws EntityExistsException, IllegalArgumentRequestException {
        validationUtil.validateDTO(offerDTO);
        try {
            return mapper.toDTO(offerRepository.saveAndFlush(mapper.toEntity(offerDTO)));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getLocalizedMessage());
        }
    }
}