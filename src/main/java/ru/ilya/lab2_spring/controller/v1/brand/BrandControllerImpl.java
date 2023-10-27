package ru.ilya.lab2_spring.controller.v1.brand;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.ilya.lab2_spring.controller.v1.BrandControllerBase;
import ru.ilya.lab2_spring.dto.BrandDTO;
import ru.ilya.lab2_spring.service.BrandService;
import ru.ilya.lab2_spring.service.util.ValidationUtil;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

@RestController
@Slf4j
public class BrandControllerImpl extends BrandControllerBase implements BrandController{

    @Autowired
    public BrandControllerImpl(BrandService brandService, ValidationUtil validationUtil) {
        super(brandService, validationUtil);
    }

    @Override
    public ResponseEntity<BrandDTO> createBrand(BrandDTO brandDTO) throws IllegalArgumentRequestException {
        return super.createBrand(brandDTO);
    }
}
