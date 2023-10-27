//package ru.ilya.lab2_spring.initializer;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import ru.ilya.lab2_spring.dto.BrandDTO;
//import ru.ilya.lab2_spring.dto.ModelDTO;
//import ru.ilya.lab2_spring.dto.UserDTO;
//import ru.ilya.lab2_spring.dto.UserRoleDTO;
//import ru.ilya.lab2_spring.service.*;
//
//import java.time.LocalDateTime;
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//    private final BrandService brandService;
//    private final OfferService offerService;
//    private final UserService userService;
//    private final ModelService modelService;
//    private final UserRoleService userRoleService;
//
//    @Autowired
//    public DataInitializer(BrandService brandService, OfferService offerService, UserService userService, ModelService modelService, UserRoleService userRoleService) {
//        this.brandService = brandService;
//        this.offerService = offerService;
//        this.userService = userService;
//        this.modelService = modelService;
//        this.userRoleService = userRoleService;
//    }
//
//    @Override
//    public void run(String... args) {
//        generateData();
//    }
//
//    private void generateData() {
//        BrandDTO brandDTO = BrandDTO.builder()
//                .name("BMW")
//                .modified(LocalDateTime.now())
//                .build();
//        BrandDTO brandDTO1  =BrandDTO.builder()
//                .name("Porsche")
//                .modified(LocalDateTime.now())
//                .build();
//        ModelDTO modelDTO = ModelDTO.builder()
//                .name("M5")
//                .imageUrl("qwerty")
//                .startYear(2022)
//                .endYear(2023)
//                .category("CAR")
//                .brandDTO(brandDTO)
//                .build();
//        ModelDTO modelDTO1 = ModelDTO.builder()
//                .name("Cayenne")
//                .brandDTO(brandDTO1)
//                .endYear(2023)
//                .startYear(2020)
//                .category("CAR")
//                .build();
//        modelService.addModel(modelDTO1);
//        modelService.addModel(modelDTO);
//        UserRoleDTO userRoleDTO = UserRoleDTO.builder()
//                .role("ADMIN")
//                .build();
//        UserRoleDTO userRoleDTO1 = UserRoleDTO.builder()
//                .role("USER")
//                .build();
//        UserDTO userDTO = UserDTO.builder()
//                .userRoleDTO(userRoleDTO)
//                .firstName("Max")
//                .lastName("Bol")
//                .password("123")
//                .created(LocalDateTime.now())
//                .isActive(true)
//                .imageUrl("cdc")
//                .username("qwerty")
//                .modified(LocalDateTime.now())
//                .build();
//        userService.createUser(userDTO);
//    }
//
//}
