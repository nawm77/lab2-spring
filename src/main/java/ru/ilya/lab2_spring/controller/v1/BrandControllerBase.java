package ru.ilya.lab2_spring.controller.v1;

import jakarta.persistence.EntityExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import ru.ilya.lab2_spring.dto.BrandDTO;
import ru.ilya.lab2_spring.service.BrandService;
import ru.ilya.lab2_spring.service.util.ValidationUtil;

@Slf4j
public abstract class BrandControllerBase {
    private final BrandService brandService;
    private final ValidationUtil validationUtil;

    @Autowired
    protected BrandControllerBase(BrandService brandService, ValidationUtil validationUtil) {
        this.brandService = brandService;
        this.validationUtil = validationUtil;
    }

    protected ResponseEntity<BrandDTO> createBrand(BrandDTO brandDTO){
        if (!validationUtil.isValid(brandDTO)){
            log.warn("Incorrect entity {}", brandDTO);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect entity");
        }
        try {
            brandService.save(brandDTO);
        } catch (EntityExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getLocalizedMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(brandDTO);
    }
}
