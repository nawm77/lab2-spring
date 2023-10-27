package ru.ilya.lab2_spring.service;

import ru.ilya.lab2_spring.dto.ModelDTO;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import java.util.List;

public interface ModelService extends BaseService<ModelDTO> {
    ModelDTO findById(String id);
    List<ModelDTO> findAll();
    List<ModelDTO> findAllByName(String name);
    ModelDTO save(ModelDTO model) throws IllegalArgumentRequestException;
    void updateModel(ModelDTO model);
    void deleteModel(ModelDTO model);
    void deleteModelById(String id);
}

