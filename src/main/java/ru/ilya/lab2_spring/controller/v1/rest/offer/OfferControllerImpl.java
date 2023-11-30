package ru.ilya.lab2_spring.controller.v1.rest.offer;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.ilya.lab2_spring.dto.OfferDTO;
import ru.ilya.lab2_spring.service.OfferService;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

@RestController
public class OfferControllerImpl extends OfferControllerBase implements  OfferController{
    @Autowired
    protected OfferControllerImpl(OfferService offerService, MeterRegistry meterRegistry) {
        super(offerService, meterRegistry);
    }

    @Override
    public ResponseEntity<OfferDTO> createOffer(OfferDTO offerDTO) throws IllegalArgumentRequestException {
        return super.createOffer(offerDTO);
    }

    @Override
    public ResponseEntity<?> getOffer(String offerId) {
        return super.getOffer(offerId);
    }

    @Override
    public ResponseEntity<OfferDTO> updateOffer(OfferDTO offerDTO) throws IllegalArgumentRequestException {
        return super.updateOffer(offerDTO);
    }

    @Override
    public ResponseEntity<?> deleteOffer(String id) {
        return super.deleteOffer(id);
    }
}
