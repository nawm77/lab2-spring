package ru.ilya.lab2_spring.controller.v1.mvc.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ilya.lab2_spring.service.OfferService;

import static ru.ilya.lab2_spring.model.api.ApiConstants.OFFER_PATH;

@Controller
@RequestMapping(OFFER_PATH)
public class OfferControllerMVC extends OfferControllerBaseMvc {
    private final OfferService offerService;
    @Autowired
    public OfferControllerMVC(OfferService offerService) {
        super(offerService);
        this.offerService = offerService;
    }
}
