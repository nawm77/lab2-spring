package ru.ilya.lab2_spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilya.lab2_spring.entity.Model;
import ru.ilya.lab2_spring.repository.ModelRepository;
import ru.ilya.lab2_spring.service.ModelService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public Model findById(Long id) {
        return modelRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such model with id" + id));
    }

    @Override
    public List<Model> findAll() {
        return modelRepository.findAll();
    }

    @Override
    public void addModel(Model model) {
        modelRepository.saveAndFlush(model);
    }

    @Override
    public void updateModel(Model model) {
        modelRepository.saveAndFlush(model);
    }

    @Override
    public void deleteModel(Model model) {
        modelRepository.delete(model);
    }

    @Override
    public void deleteModelById(Long id) {
        modelRepository.deleteById(id);
    }
}

