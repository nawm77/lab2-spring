package ru.ilya.lab2_spring.controller.v1.mvc.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ilya.lab2_spring.dto.ModelDTO;
import ru.ilya.lab2_spring.service.ModelService;

import static ru.ilya.lab2_spring.model.api.ApiConstants.ALL_PATH;
import static ru.ilya.lab2_spring.model.api.ApiConstants.EDIT_PATH;

public class ModelControllerBaseMvc {
    private final ModelService modelService;

    @Autowired
    public ModelControllerBaseMvc(ModelService modelService) {
        this.modelService = modelService;
    }

    @ModelAttribute("model")
    public ModelDTO initModel() {
        return new ModelDTO();
    }

    @RequestMapping(value = ALL_PATH, method = RequestMethod.GET)
    public String getAllModels(Model model) {
        model.addAttribute("models", modelService.findAll());
        return "model/models";
    }

    @RequestMapping(value = EDIT_PATH, method = RequestMethod.GET)
    public String editModel() {
        return "model/model-edit";
    }

//    @RequestMapping(value = EDIT_PATH, method = RequestMethod.GET)
//    public String editRedirect(@Valid ModelDTO modelDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//        return MessageFormat.format("{0}{1}{2}", REDIRECT_PATH, MODEL_PATH, ALL_PATH);
//    }
}
