package ru.ilya.lab2_spring.service;

import ru.ilya.lab2_spring.entity.Model;

import java.util.List;
import java.util.UUID;

public interface ModelService {
    Model findById(UUID id);
    List<Model> findAll();
    void addModel(Model model);
    void updateModel(Model model);
    void deleteModel(Model model);
    void deleteModelById(UUID id);
}

