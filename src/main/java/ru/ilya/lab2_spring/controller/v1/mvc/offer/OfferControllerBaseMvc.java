package ru.ilya.lab2_spring.controller.v1.mvc.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ilya.lab2_spring.service.OfferService;

import static ru.ilya.lab2_spring.model.api.ApiConstants.ALL_PATH;

public class OfferControllerBaseMvc {
    private final OfferService offerService;

    @Autowired
    public OfferControllerBaseMvc(OfferService offerService) {
        this.offerService = offerService;
    }

    @RequestMapping(ALL_PATH)
    public String getAllOffers(Model model) {
        model.addAttribute("offers", offerService.findAll());
        return "offer/offers";
    }
}
