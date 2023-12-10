package ru.ilya.lab2_spring.controller.v1.mvc.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ilya.lab2_spring.service.ModelService;

import static ru.ilya.lab2_spring.model.api.ApiConstants.MODEL_PATH;

@Controller
@RequestMapping(MODEL_PATH)
public class ModelControllerMVC extends ModelControllerBaseMvc{
    @Autowired
    public ModelControllerMVC(ModelService modelService) {
        super(modelService);
    }
}
