package ru.ilya.lab2_spring.service.impl;

import jakarta.persistence.EntityExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.ilya.lab2_spring.dto.OfferDTO;
import ru.ilya.lab2_spring.mapper.Mapper;
import ru.ilya.lab2_spring.repository.OfferRepository;
import ru.ilya.lab2_spring.service.OfferService;
import ru.ilya.lab2_spring.service.util.ValidationUtil;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class OfferServiceImpl implements OfferService {
    private final Mapper mapper;
    private final ValidationUtil validationUtil;
    private OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(Mapper mapper, ValidationUtil validationUtil) {
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }

    @Autowired
    public void setOfferRepository(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public OfferDTO findById(String id) {
        return mapper.toDTO(offerRepository.findById(id).orElseThrow(() -> new NoSuchElementException(String.format("No such offer with id: %s", id))));
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
    @PostFilter("filterObject.userDTO.username eq principal.username or principal.authorities.contains('admin:read')")
    public List<OfferDTO> findAll() {
        return new ArrayList<>(offerRepository.findAll().stream()
                .map(mapper::toDTO)
                .toList());
    }

    private OfferDTO saveOrUpdate(OfferDTO offerDTO) throws EntityExistsException, IllegalArgumentRequestException {
        offerDTO.setModified(LocalDateTime.now().toString());
        validationUtil.validateDTO(offerDTO);
        try {
            return mapper.toDTO(offerRepository.saveAndFlush(mapper.toEntity(offerDTO)));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getLocalizedMessage());
        }
    }
}