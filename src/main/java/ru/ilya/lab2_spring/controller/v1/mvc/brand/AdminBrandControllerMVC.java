package ru.ilya.lab2_spring.controller.v1.mvc.brand;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ilya.lab2_spring.service.BrandService;

import static ru.ilya.lab2_spring.model.api.ApiConstants.ADMIN_PATH;
import static ru.ilya.lab2_spring.model.api.ApiConstants.BRAND_PATH;

@Controller
@RequestMapping(value = ADMIN_PATH+BRAND_PATH)
public class AdminBrandControllerMVC extends BrandControllerBaseMvc {
    public AdminBrandControllerMVC(BrandService brandService) {
        super(brandService);
    }
}
