package ru.ilya.lab2_spring.controller.v1.mvc.brand;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ilya.lab2_spring.dto.BrandDTO;
import ru.ilya.lab2_spring.service.BrandService;

import static ru.ilya.lab2_spring.model.api.ApiConstants.ALL_PATH;
import static ru.ilya.lab2_spring.model.api.ApiConstants.SPLIT_PATH;

@Slf4j
public class BrandControllerBaseMvc {
    private final BrandService brandService;

    @Autowired
    public BrandControllerBaseMvc(BrandService brandService) {
        this.brandService = brandService;
    }

    @ModelAttribute("brandModel")
    public BrandDTO initBrand() {
        return new BrandDTO();
    }

    @RequestMapping(method = RequestMethod.GET, value = ALL_PATH)
    protected String getBrands(Model model) {
        model.addAttribute("brands", brandService.findAll());
        return "brands";
    }

    @RequestMapping(method = RequestMethod.GET, value = SPLIT_PATH + "{id}")
    public String getBrandById(@PathVariable("id") String id, Model model) {
        log.info("ID -> {}", id);
        model.addAttribute("brand", brandService.findByIdWithModel(id));
        return "brand-details";
    }
}
