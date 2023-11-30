package ru.ilya.lab2_spring.controller.v1.mvc.brand;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static ru.ilya.lab2_spring.model.api.ApiConstants.SPLIT_PATH;

@Controller
@RequestMapping(value = SPLIT_PATH)
public class HomeController {
    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "index";
    }
}
