package ru.ilya.lab2_spring.service;

import ru.ilya.lab2_spring.dto.UserDTO;
import ru.ilya.lab2_spring.model.viewModel.UserOfferViewModel;

import java.util.List;

public interface UserService extends BaseService<UserDTO> {
    List<UserDTO> findAllByUsername(String username);

    List<UserOfferViewModel> findAllWithOffers();

    UserOfferViewModel findByIdWithOffers(String userId);
}
