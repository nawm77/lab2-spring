package ru.ilya.lab2_spring.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.ilya.lab2_spring.entity.*;
import ru.ilya.lab2_spring.entity.enums.Category;
import ru.ilya.lab2_spring.entity.enums.Engine;
import ru.ilya.lab2_spring.entity.enums.Role;
import ru.ilya.lab2_spring.entity.enums.Transmission;
import ru.ilya.lab2_spring.repository.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    @Autowired
    public DataInitializer(BrandRepository brandRepository, ModelRepository modelRepository, OfferRepository offerRepository, UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void run(String... args) {
        generateData();
    }

    private void generateData(){
        generateUserRoles();
        generateBrands();
        generateModels();
        generateUsers();
        generateOffers();
    }

    private void generateUserRoles() {
        userRoleRepository.saveAndFlush(UserRole.builder()
                .role(Role.USER)
                .build());
        userRoleRepository.saveAndFlush(UserRole.builder()
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
            brandRepository.save(brand);
        }
    }

    private void generateModels() {
        List<Category> categoryList = List.of(Category.Motorcycle, Category.Bus, Category.Car, Category.Truck);
        for (int i = 1; i <= 5; i++) {
            Brand brand = brandRepository.findById((long) i).orElse(null);
            if (brand != null) {
                Model model = Model.builder()
                        .name("Model " + i)
                        .category(categoryList.get(i%categoryList.size()))
                        .imageUrl("image_url_" + i)
                        .startYear(new Date())
                        .endYear(new Date())
                        .created(LocalDateTime.now())
                        .modified(LocalDateTime.now())
                        .brand(brand)
                        .build();
                modelRepository.save(model);
            }
        }
    }

    private void generateOffers() {
        List<Engine> engineList = List.of(Engine.GASOLINE, Engine.DIESEL, Engine.ELECTRIC, Engine.HYBRID);
        List<Transmission> transmissionList = List.of(Transmission.AUTOMATIC, Transmission.MANUAL);
        List<Model> models = modelRepository.findAll();
        List<User> users = userRepository.findAll();
        for (int i = 1; i <= 5; i++) {
            Model model = models.get(i%models.size());
            User seller = users.get(i%users.size());
            Offer offer = Offer.builder()
                    .description("Offer " + i)
                    .engine(engineList.get(i%engineList.size()))
                    .imageUrl("image_url_" + i)
                    .mileage(10000 * i)
                    .price(10000 * i)
                    .transmission(transmissionList.get(i% transmissionList.size()))
                    .year((short) (2020 + i))
                    .created(LocalDateTime.now())
                    .modified(LocalDateTime.now())
                    .model(model)
                    .seller(seller)
                    .build();
            offerRepository.save(offer);
        }
    }

    private void generateUsers() {
        List<UserRole> roles = userRoleRepository.findAll();
        for (int i = 1; i <= 5; i++) {
            UserRole role = roles.get(i%2);
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
                userRepository.save(user);
            }
        }
    }
}
