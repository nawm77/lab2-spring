package ru.ilya.lab2_spring.controller.v1.mvc.brand;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.ilya.lab2_spring.dto.BrandDTO;
import ru.ilya.lab2_spring.service.BrandService;

import java.text.MessageFormat;

import static ru.ilya.lab2_spring.model.api.ApiConstants.*;

@Controller
@RequestMapping(value = ADMIN_PATH+BRAND_PATH)
public class AdminBrandControllerMVC extends BrandControllerBaseMvc {
    private final BrandService brandService;
    public AdminBrandControllerMVC(BrandService brandService, BrandService brandService1) {
        super(brandService);
        this.brandService = brandService1;
    }

    @RequestMapping(method = RequestMethod.GET, value = EDIT_PATH+SPLIT_PATH+"{id}")
    public String updateBrand(@PathVariable String id, Model model) {
        model.addAttribute("brandModel",brandService.findById(id));
        return "brands-update";
    }

    @RequestMapping(method = RequestMethod.POST, value = EDIT_PATH+SPLIT_PATH+"{id}")
    public String sendUpdate(@Valid BrandDTO brandDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("brandModel", brandDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandModel",
                    bindingResult);
            return MessageFormat.format("{0}{1}{2}", REDIRECT_PATH, BRAND_PATH, EDIT_PATH);
        }
        try {
            brandService.update(brandDTO);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ошибка создания бренда. Бренд уже существует");
            redirectAttributes.addFlashAttribute("brandModel", brandDTO);
            return MessageFormat.format("{0}{1}{2}", REDIRECT_PATH, BRAND_PATH, EDIT_PATH);
        }
        return MessageFormat.format("{0}{1}{2}", REDIRECT_PATH, BRAND_PATH, ALL_PATH);
    }

    @RequestMapping(method = RequestMethod.GET, value = CREATE_PATH)
    protected String createBrand() {
        return "brands-create";
    }

    @RequestMapping(method = RequestMethod.POST, value = CREATE_PATH)
    protected String addBrand(@Valid BrandDTO brandDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
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
}
