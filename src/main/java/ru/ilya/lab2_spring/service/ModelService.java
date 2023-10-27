package ru.ilya.lab2_spring.service;

import ru.ilya.lab2_spring.dto.ModelDTO;

import java.util.List;

public interface ModelService extends BaseService<ModelDTO> {
    ModelDTO findById(String id);
    List<ModelDTO> findAll();
    List<ModelDTO> findAllByName(String name);
    void addModel(ModelDTO model);
    void updateModel(ModelDTO model);
    void deleteModel(ModelDTO model);
    void deleteModelById(String id);
}

