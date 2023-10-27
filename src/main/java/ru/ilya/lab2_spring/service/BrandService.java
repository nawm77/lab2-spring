package ru.ilya.lab2_spring.service;

import ru.ilya.lab2_spring.dto.BrandDTO;
import ru.ilya.lab2_spring.model.viewModel.BrandModelViewModel;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import java.util.List;

public interface BrandService extends BaseService<BrandDTO> {
    List<BrandDTO> findAllByName(String name);
    void delete(BrandDTO brand) throws IllegalArgumentRequestException;
    List<BrandModelViewModel> findAllWithModels();
    BrandModelViewModel findByIdWithModel(String id);
}
