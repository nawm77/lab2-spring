package ru.ilya.lab2_spring.service;

import ru.ilya.lab2_spring.dto.ModelDTO;

import java.util.List;

public interface ModelService extends BaseService<ModelDTO> {
    List<ModelDTO> findAllByName(String name);
}

