package ru.ilya.lab2_spring.controller.v1.rest.brand;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import ru.ilya.lab2_spring.dto.BrandDTO;
import ru.ilya.lab2_spring.service.BrandService;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import java.util.HashSet;
import java.util.Set;

import static ru.ilya.lab2_spring.meter.MeterConstants.BRANDS_TOTAL_COUNT;

@Slf4j
public abstract class BrandControllerBase {
    private final BrandService brandService;
    private final MeterRegistry meterRegistry;

    @Autowired
    protected BrandControllerBase(BrandService brandService, MeterRegistry meterRegistry) {
        this.brandService = brandService;
        this.meterRegistry = meterRegistry;
    }

    protected ResponseEntity<BrandDTO> createBrand(BrandDTO brandDTO) throws IllegalArgumentRequestException {
        try {
            BrandDTO b = brandService.save(brandDTO);
            Counter.builder(BRANDS_TOTAL_COUNT).description("Total count of created brands").register(meterRegistry).increment();
            return ResponseEntity.status(HttpStatus.CREATED).body(b);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getLocalizedMessage());
        }
    }

    protected ResponseEntity<?> getBrand(String id, Boolean withModels) {
        if ("-1".equals(id)) {
            if (!withModels) {
                return ResponseEntity.status(HttpStatus.FOUND).body(brandService.findAll());
            } else {
                return ResponseEntity.status(HttpStatus.FOUND).body(brandService.findAllWithModels());
            }
        } else {
            if (!withModels) {
                return ResponseEntity.status(HttpStatus.FOUND).body(brandService.findById(id));
            } else {
                return ResponseEntity.status(HttpStatus.FOUND).body(brandService.findByIdWithModel(id));
            }
        }
    }

    protected ResponseEntity<BrandDTO> updateBrand(BrandDTO brandDTO) throws IllegalArgumentRequestException {
        HttpStatus status = brandService.findAllByName(brandDTO.getName()).isEmpty() ?
                HttpStatus.CREATED : HttpStatus.ACCEPTED;
        brandService.update(brandDTO);
        return ResponseEntity.status(status).body(brandDTO);
    }

    protected ResponseEntity<?> deleteBrand(String id) {
        brandService.deleteById(id);
        return ResponseEntity.ok(id);
    }
}
