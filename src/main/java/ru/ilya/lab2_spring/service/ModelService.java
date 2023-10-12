package ru.ilya.lab2_spring.service;

import ru.ilya.lab2_spring.entity.Model;

import java.util.List;

public interface ModelService {
    Model findById(Long id);
    List<Model> findAll();
    void addModel(Model model);
    void updateModel(Model model);
    void deleteModel(Model model);
    void deleteModelById(Long id);
}

