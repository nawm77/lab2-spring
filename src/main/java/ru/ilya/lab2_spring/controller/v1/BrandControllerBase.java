package ru.ilya.lab2_spring.controller.v1;

import jakarta.persistence.EntityExistsException;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import ru.ilya.lab2_spring.dto.BrandDTO;
import ru.ilya.lab2_spring.service.BrandService;
import ru.ilya.lab2_spring.service.util.ValidationUtil;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class BrandControllerBase {
    private final BrandService brandService;
    private final ValidationUtil validationUtil;
    private final List<String> exceptions = new ArrayList<>();

    @Autowired
    protected BrandControllerBase(BrandService brandService, ValidationUtil validationUtil) {
        this.brandService = brandService;
        this.validationUtil = validationUtil;
    }

    protected ResponseEntity<BrandDTO> createBrand(BrandDTO brandDTO) throws IllegalArgumentRequestException {
        if (!validationUtil.isValid(brandDTO)) {
            exceptions.clear();
            validationUtil.violations(brandDTO).stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(exceptions::add);
            log.warn("Incorrect entity {}", brandDTO);
            throw new IllegalArgumentRequestException(exceptions);
        }
        BrandDTO b;
        try {
            b = brandService.save(brandDTO);
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getLocalizedMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(b);
    }

    protected ResponseEntity<BrandDTO> getBrand(String id){
        return ResponseEntity.status(HttpStatus.FOUND).body(brandService.findById(id));
    }
}
