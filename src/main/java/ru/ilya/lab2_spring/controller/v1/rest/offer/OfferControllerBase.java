package ru.ilya.lab2_spring.controller.v1.rest.offer;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import ru.ilya.lab2_spring.dto.OfferDTO;
import ru.ilya.lab2_spring.service.OfferService;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import static ru.ilya.lab2_spring.meter.MeterConstants.OFFERS_TOTAL_COUNT;

@Slf4j
public abstract class OfferControllerBase {
    private final OfferService offerService;
    private final MeterRegistry meterRegistry;

    @Autowired
    protected OfferControllerBase(OfferService offerService, MeterRegistry meterRegistry) {
        this.offerService = offerService;
        this.meterRegistry = meterRegistry;
    }

    protected ResponseEntity<OfferDTO> createOffer(OfferDTO offerDTO) throws IllegalArgumentRequestException {
        OfferDTO offer;
        try {
            offer = offerService.save(offerDTO);
            Counter.builder(OFFERS_TOTAL_COUNT).description("Total count of created offers").register(meterRegistry).increment();
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
        HttpStatus status = offerService.existsById(offerDTO.getId()) ? HttpStatus.ACCEPTED : HttpStatus.CREATED;
        offerService.update(offerDTO);
        return ResponseEntity.status(status).body(offerDTO);
    }

    protected ResponseEntity<?> deleteOffer(String id) {
        offerService.deleteById(id);
        return ResponseEntity.ok(id);
    }
}
