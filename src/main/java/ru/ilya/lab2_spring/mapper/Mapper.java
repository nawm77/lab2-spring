package ru.ilya.lab2_spring.mapper;

import ru.ilya.lab2_spring.dto.*;
import ru.ilya.lab2_spring.model.*;

public interface Mapper {
    Model toEntity(ModelDTO modelDTO);
    ModelDTO toDTO(Model model);
    Offer toEntity(OfferDTO offerDTO);
    OfferDTO toDTO(Offer offer);
    Brand toEntity(BrandDTO brandDTO);
    BrandDTO toDTO(Brand brand);
    User toEntity(UserDTO userDTO);
    UserDTO toDTO(User user);
    UserRole toEntity(UserRoleDTO userRoleDTO);
    UserRoleDTO toDTO(UserRole userRole);
}
