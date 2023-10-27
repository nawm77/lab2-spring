package ru.ilya.lab2_spring.controller.v1.brand;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import ru.ilya.lab2_spring.dto.BrandDTO;
import ru.ilya.lab2_spring.service.BrandService;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public abstract class BrandControllerBase {
    private final BrandService brandService;

    @Autowired
    protected BrandControllerBase(BrandService brandService) {
        this.brandService = brandService;
    }

    protected ResponseEntity<BrandDTO> createBrand(BrandDTO brandDTO) throws IllegalArgumentRequestException {
        brandDTO.setModified(LocalDateTime.now());
        BrandDTO b;
        try {
            b = brandService.save(brandDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(b);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getLocalizedMessage());
        }
    }

    protected ResponseEntity<?> getBrand(String id, Boolean withModels){
        if("-1".equals(id)){
            if(!withModels) {
                return ResponseEntity.status(HttpStatus.FOUND).body(brandService.findAll());
            } else {
                return ResponseEntity.status(HttpStatus.FOUND).body(brandService.findAllWithModels());
            }
        } else{
            if(!withModels) {
                return ResponseEntity.status(HttpStatus.FOUND).body(List.of(brandService.findById(id)));
            } else {
                return ResponseEntity.status(HttpStatus.FOUND).body(brandService.findByIdWithModel(id));
            }
        }
    }

    protected ResponseEntity<BrandDTO> updateBrand(BrandDTO brandDTO) throws IllegalArgumentRequestException {
        brandDTO.setModified(LocalDateTime.now());
        HttpStatus status = brandService.findAllByName(brandDTO.getName()).isEmpty() ? HttpStatus.CREATED : HttpStatus.ACCEPTED;
        brandService.update(brandDTO);
        return ResponseEntity.status(status).body(brandDTO);
    }

    protected ResponseEntity<?> deleteBrand(String id) {
        brandService.deleteById(id);
        return ResponseEntity.ok(id);
    }
}
