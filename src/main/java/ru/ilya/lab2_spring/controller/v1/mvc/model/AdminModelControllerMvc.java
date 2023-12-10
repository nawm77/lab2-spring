package ru.ilya.lab2_spring.controller.v1.mvc.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ilya.lab2_spring.model.enums.Category;
import ru.ilya.lab2_spring.service.ModelService;

import java.text.MessageFormat;
import java.util.Arrays;

import static ru.ilya.lab2_spring.model.api.ApiConstants.*;

@Controller
@RequestMapping(ADMIN_PATH+MODEL_PATH)
public class AdminModelControllerMvc extends ModelControllerBaseMvc{
    private final ModelService modelService;
    @Autowired
    public AdminModelControllerMvc(ModelService modelService) {
        super(modelService);
        this.modelService = modelService;
    }

    @RequestMapping(value = CREATE_PATH, method = RequestMethod.GET)
    public String createModel(Model model) {
        model.addAttribute("existingCategory", Arrays.stream(Category.values()).toList());
        return "model/model-create";
    }

    @RequestMapping(value = CREATE_PATH, method = RequestMethod.POST)
    public String saveModel(Model model) {
        return MessageFormat.format("{0}{1}{2}", REDIRECT_PATH, SPLIT_PATH, MODEL_PATH);
    }

    @RequestMapping(value = EDIT_PATH+SPLIT_PATH+"{id}", method = RequestMethod.GET)
    public String editModel(Model model, @PathVariable("id") String id) {
        model.addAttribute("model", modelService.findById(id));
        return "model/model-edit";
    }
}
