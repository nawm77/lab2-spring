package ru.ilya.lab2_spring.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilya.lab2_spring.dto.*;
import ru.ilya.lab2_spring.model.*;
import ru.ilya.lab2_spring.mapper.Mapper;
import ru.ilya.lab2_spring.model.viewModel.ModelWithOutBrandView;

@Service
public class MapperImpl implements Mapper {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Model toEntity(ModelDTO modelDTO) {
        return modelMapper.map(modelDTO, Model.class);
    }

    @Override
    public ModelDTO toDTO(Model model) {
        ModelDTO dto = modelMapper.map(model, ModelDTO.class);
        dto.setBrandDTO(toDTO(model.getBrand()));
        return dto;
    }

    @Override
    public Offer toEntity(OfferDTO offerDTO) {
        return modelMapper.map(offerDTO, Offer.class);
    }

    @Override
    public OfferDTO toDTO(Offer offer) {
        return modelMapper.map(offer, OfferDTO.class);
    }

    @Override
    public Brand toEntity(BrandDTO brandDTO) {
        return modelMapper.map(brandDTO, Brand.class);
    }

    @Override
    public BrandDTO toDTO(Brand brand) {
        return modelMapper.map(brand, BrandDTO.class);
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    @Override
    public UserDTO toDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserRole toEntity(UserRoleDTO userRoleDTO) {
        return modelMapper.map(userRoleDTO, UserRole.class);
    }

    @Override
    public UserRoleDTO toDTO(UserRole userRole) {
        return modelMapper.map(userRole, UserRoleDTO.class);
    }

    @Override
    public ModelWithOutBrandView toView(Model model) {
        return modelMapper.map(model, ModelWithOutBrandView.class);
    }
}
