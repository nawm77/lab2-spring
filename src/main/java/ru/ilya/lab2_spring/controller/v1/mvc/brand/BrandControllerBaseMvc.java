package ru.ilya.lab2_spring.controller.v1.mvc.brand;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.ilya.lab2_spring.dto.BrandDTO;
import ru.ilya.lab2_spring.service.BrandService;

import java.text.MessageFormat;

import static ru.ilya.lab2_spring.model.api.ApiConstants.*;

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
    protected String getBrands(Model model, HttpServletRequest request) {
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("isAdmin", extractRole(request));
        return "brands";
    }

    @RequestMapping(method = RequestMethod.GET, value = CREATE_PATH)
    protected String createBrand(Model model, HttpServletRequest request) {
        return "brands-create";
    }

    @RequestMapping(method = RequestMethod.POST, value = CREATE_PATH)
    protected String addBrand(@Valid BrandDTO brandDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("brandModel", brandDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandModel",
                    bindingResult);
            return MessageFormat.format("{0}{1}{2}", REDIRECT_PATH, BRAND_PATH, CREATE_PATH);
        }
        try {
            brandService.save(brandDTO);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ошибка создания бренда. Бренд уже существует");
            redirectAttributes.addFlashAttribute("brandModel", brandDTO);
            return MessageFormat.format("{0}{1}{2}", REDIRECT_PATH, BRAND_PATH, CREATE_PATH);
        }
        return MessageFormat.format("{0}{1}{2}", REDIRECT_PATH, BRAND_PATH, ALL_PATH);
    }

    @RequestMapping(method = RequestMethod.GET, value = EDIT_PATH)
    public String updateBrand(Model model) {
        return "index";
    }

    private Boolean extractRole(HttpServletRequest request) {
        return "admin".equals(request.getRequestURI().split("/")[1]);
    }
}
