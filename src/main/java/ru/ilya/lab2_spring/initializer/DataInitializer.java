package ru.ilya.lab2_spring.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.ilya.lab2_spring.entity.*;
import ru.ilya.lab2_spring.entity.enums.Category;
import ru.ilya.lab2_spring.entity.enums.Engine;
import ru.ilya.lab2_spring.entity.enums.Role;
import ru.ilya.lab2_spring.entity.enums.Transmission;
import ru.ilya.lab2_spring.service.*;

import java.time.LocalDateTime;
import java.util.List;

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
        generateUserRoles();
        generateBrands();
        generateModels();
        generateUsers();
        generateOffers();
    }

    private void generateUserRoles() {
        userRoleService.addUserRole(UserRole.builder()
                .role(Role.USER)
                .build());
        userRoleService.addUserRole(UserRole.builder()
                .role(Role.ADMIN)
                .build());
    }

    private void generateBrands() {
        for (int i = 1; i <= 5; i++) {
            Brand brand = Brand.builder()
                    .name("Brand " + i)
                    .created(LocalDateTime.now())
                    .modified(LocalDateTime.now())
                    .build();
            brandService.save(brand);
        }
    }

    private void generateModels() {
        List<Category> categoryList = List.of(Category.MOTORCYCLE, Category.BUS, Category.CAR, Category.TRUCK);
        List<Brand> brandList = brandService.findAll();
        for (int i = 0; i < 5; i++) {
            Brand brand = brandList.get(i);
            if (brand != null) {
                Model model = Model.builder()
                        .name("Model " + i)
                        .category(categoryList.get(i%categoryList.size()))
                        .imageUrl("image_url_" + i)
                        .startYear(2022)
                        .endYear(2023)
                        .created(LocalDateTime.now())
                        .modified(LocalDateTime.now())
                        .brand(brand)
                        .build();
                modelService.addModel(model);
            }
        }
    }

    private void generateOffers() {
        List<Engine> engineList = List.of(Engine.GASOLINE, Engine.DIESEL, Engine.ELECTRIC, Engine.HYBRID);
        List<Transmission> transmissionList = List.of(Transmission.AUTOMATIC, Transmission.MANUAL);
        List<Model> models = modelService.findAll();
        List<User> users = userService.findAll();
        Model model;
        User seller;
        Offer offer;
        for (int i =0; i < 5; i++) {
            model = models.get(i%models.size());
            seller = users.get(i%users.size());
            offer = Offer.builder()
                    .description("Offer " + i)
                    .engine(engineList.get(i%engineList.size()))
                    .imageUrl("image_url_" + i)
                    .mileage(10000 * i)
                    .price(10000 * i)
                    .transmission(transmissionList.get(i% transmissionList.size()))
                    .year(2010+i)
                    .created(LocalDateTime.now())
                    .modified(LocalDateTime.now())
                    .model(model)
                    .seller(seller)
                    .build();
            offerService.addOffer(offer);
        }
    }

    private void generateUsers() {
        List<UserRole> roles = userRoleService.findAll();
        for (int i = 0; i < 5; i++) {
            UserRole role = roles.get(i % 2);
            if (role != null) {
                User user = User.builder()
                        .username("user" + i)
                        .password("password" + i)
                        .firstName("First" + i)
                        .lastName("Last" + i)
                        .isActive(true)
                        .role(role)
                        .imageUrl("image_url_" + i)
                        .created(LocalDateTime.now())
                        .modified(LocalDateTime.now())
                        .build();
                userService.createUser(user);
            }
        }
    }
}
