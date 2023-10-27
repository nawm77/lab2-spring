package ru.ilya.lab2_spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilya.lab2_spring.dto.ModelDTO;
import ru.ilya.lab2_spring.model.Model;
import ru.ilya.lab2_spring.model.enums.Category;
import ru.ilya.lab2_spring.mapper.Mapper;
import ru.ilya.lab2_spring.repository.ModelRepository;
import ru.ilya.lab2_spring.service.ModelService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final Mapper mapper;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, Mapper mapper) {
        this.modelRepository = modelRepository;
        this.mapper = mapper;
    }

    @Override
    public ModelDTO findById(String id) {
        return mapper.toDTO(modelRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such model with id" + id)));
    }

    @Override
    public void deleteById(String id) {
        modelRepository.deleteById(id);
    }

    @Override
    public ModelDTO update(ModelDTO object) {
        return null;
    }

    @Override
    public List<ModelDTO> findAll() {
        return modelRepository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public void addModel(ModelDTO model) {
        modelRepository.save(mapper.toEntity(model));
    }

    @Override
    public void updateModel(ModelDTO model) {
        Model existingModel = mapper.toEntity(findById(model.getId()));
        if(model.getBrandDTO() != null){
            existingModel.setBrand(mapper.toEntity(model.getBrandDTO()));
        }
        if(model.getCategory() != null){
            existingModel.setCategory(Category.valueOf(model.getCategory()));
        }
        if(model.getName() != null){
            existingModel.setName(model.getName());
        }
        if(model.getImageUrl() != null){
            existingModel.setImageUrl(model.getImageUrl());
        }
        modelRepository.save(mapper.toEntity(model));
    }

    @Override
    public void deleteModel(ModelDTO model) {
        modelRepository.delete(mapper.toEntity(model));
    }

    @Override
    public void deleteModelById(String id) {
        modelRepository.deleteById(id);
    }
}

