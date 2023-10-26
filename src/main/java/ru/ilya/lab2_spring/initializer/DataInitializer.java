package ru.ilya.lab2_spring.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.ilya.lab2_spring.dto.BrandDTO;
import ru.ilya.lab2_spring.dto.ModelDTO;
import ru.ilya.lab2_spring.dto.UserDTO;
import ru.ilya.lab2_spring.dto.UserRoleDTO;
import ru.ilya.lab2_spring.entity.enums.Category;
import ru.ilya.lab2_spring.service.*;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {
    private final BrandService brandService;
    private final OfferService offerService;
    private final UserService userService;
    private final ModelService modelService;
    private final UserRoleService userRoleService;

    @Autowired
    public DataInitializer(BrandService brandService, OfferService offerService, UserService userService, ModelService modelService, UserRoleService userRoleService) {
        this.brandService = brandService;
        this.offerService = offerService;
        this.userService = userService;
        this.modelService = modelService;
        this.userRoleService = userRoleService;
    }

    @Override
    public void run(String... args) {
        generateData();
    }

    private void generateData() {
        BrandDTO brandDTO = BrandDTO.builder()
                .name("BMW")
                .modified(LocalDateTime.now())
                .created(LocalDateTime.now())
                .build();
        BrandDTO brandDTO1  =BrandDTO.builder()
                .name("Porsche")
                .modified(LocalDateTime.now())
                .created(LocalDateTime.now())
                .build();
        ModelDTO modelDTO = ModelDTO.builder()
                .name("M5")
                .imageUrl("qwerty")
                .startYear(2022)
                .endYear(2023)
                .category("CAR")
                .modified(LocalDateTime.now())
                .created(LocalDateTime.now())
                .brandDTO(brandDTO)
                .build();
        modelService.addModel(modelDTO);
        brandService.delete(brandDTO);
        UserRoleDTO userRoleDTO = UserRoleDTO.builder()
                .role("ADMIN")
                .build();
        UserRoleDTO userRoleDTO1 = UserRoleDTO.builder()
                .role("USER")
                .build();
        UserDTO userDTO = UserDTO.builder()
                .userRoleDTO(userRoleDTO)
                .build();
    }

}
