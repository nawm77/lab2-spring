package ru.ilya.lab2_spring.controller.v1.offer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import ru.ilya.lab2_spring.dto.OfferDTO;
import ru.ilya.lab2_spring.service.OfferService;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import java.time.LocalDateTime;

@Slf4j
public abstract class OfferControllerBase {
    private final OfferService offerService;

    @Autowired
    protected OfferControllerBase(OfferService offerService) {
        this.offerService = offerService;
    }

    protected ResponseEntity<OfferDTO> createOffer(OfferDTO offerDTO) throws IllegalArgumentRequestException {
        offerDTO.setModified(LocalDateTime.now());
        OfferDTO offer;
        try {
            offer = offerService.save(offerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(offer);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getLocalizedMessage());
        }
    }

    protected ResponseEntity<?> getOffer(String id) {
        if ("-1".equals(id)) {
            return ResponseEntity.status(HttpStatus.FOUND).body(offerService.findAll());
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(offerService.findById(id));
        }
    }

    protected ResponseEntity<OfferDTO> updateOffer(OfferDTO offerDTO) throws IllegalArgumentRequestException {
        offerDTO.setModified(LocalDateTime.now());
        HttpStatus status = offerService.existsById(offerDTO.getId()) ? HttpStatus.ACCEPTED : HttpStatus.CREATED;
        offerService.update(offerDTO);
        return ResponseEntity.status(status).body(offerDTO);
    }

    protected ResponseEntity<?> deleteOffer(String id) {
        offerService.deleteById(id);
        return ResponseEntity.ok(id);
    }
}
