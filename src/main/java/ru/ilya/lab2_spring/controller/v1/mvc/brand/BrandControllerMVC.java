package ru.ilya.lab2_spring.controller.v1.mvc.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ilya.lab2_spring.service.BrandService;

import static ru.ilya.lab2_spring.model.api.ApiConstants.BRAND_PATH;

@Controller
@RequestMapping(BRAND_PATH)
public class BrandControllerMVC extends BrandControllerBaseMvc {
    @Autowired
    public BrandControllerMVC(BrandService brandService) {
        super(brandService);
    }
}
