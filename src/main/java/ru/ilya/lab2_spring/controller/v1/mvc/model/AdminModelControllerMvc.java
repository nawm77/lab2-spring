package ru.ilya.lab2_spring.controller.v1.mvc.model;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ilya.lab2_spring.dto.ModelDTO;
import ru.ilya.lab2_spring.dto.ModelWithBrandID;
import ru.ilya.lab2_spring.model.enums.Category;
import ru.ilya.lab2_spring.service.BrandService;
import ru.ilya.lab2_spring.service.ModelService;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import java.util.Arrays;

import static ru.ilya.lab2_spring.model.api.ApiConstants.*;

@Controller
@RequestMapping(ADMIN_PATH+MODEL_PATH)
public class AdminModelControllerMvc extends ModelControllerBaseMvc{
    private final ModelService modelService;
    private final BrandService brandService;
    @Autowired
    public AdminModelControllerMvc(ModelService modelService, BrandService brandService) {
        super(modelService);
        this.modelService = modelService;
        this.brandService = brandService;
    }

    @ModelAttribute("modelBrandId")
    public ModelWithBrandID init(){
        return new ModelWithBrandID();
    }

    @RequestMapping(value = CREATE_PATH, method = RequestMethod.GET)
    public String createModel(Model model) {
        model.addAttribute("existingCategory", Arrays.stream(Category.values()).toList());
        model.addAttribute("brands", brandService.findAll());
        return "model-create";
    }

    @RequestMapping(value = CREATE_PATH, method = RequestMethod.POST)
    public String saveModel(@Valid ModelWithBrandID model) throws IllegalArgumentRequestException {
        System.out.println(model);
        System.out.println(brandService.findById(model.getBrandId()));
        ModelDTO modelDTO = ModelDTO.builder()
                .brandDTO(brandService.findById(model.getBrandId()))
                .endYear(model.getEndYear())
                .startYear(model.getStartYear())
                .imageUrl(model.getImageUrl())
                .category(model.getCategory())
                .modified(model.getModified())
                .name(model.getName())
                .created(model.getCreated())
                .build();
        modelService.save(modelDTO);
        return "redirect:/model/all";
    }

    @RequestMapping(value = EDIT_PATH+SPLIT_PATH+"{id}", method = RequestMethod.GET)
    public String editModel(Model model, @PathVariable("id") String id) {
        model.addAttribute("model", modelService.findById(id));
        return "model-edit";
    }
}
