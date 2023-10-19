package ru.ilya.lab2_spring.service;

import ru.ilya.lab2_spring.dto.ModelDTO;
import ru.ilya.lab2_spring.entity.Model;

import java.util.List;
import java.util.UUID;

public interface ModelService {
    ModelDTO findById(UUID id);
    List<ModelDTO> findAll();
    void addModel(ModelDTO model);
    void updateModel(ModelDTO model);
    void deleteModel(ModelDTO model);
    void deleteModelById(UUID id);
}

