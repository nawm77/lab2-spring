package ru.ilya.lab2_spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilya.lab2_spring.dto.ModelDTO;
import ru.ilya.lab2_spring.entity.Model;
import ru.ilya.lab2_spring.mapper.Mapper;
import ru.ilya.lab2_spring.repository.ModelRepository;
import ru.ilya.lab2_spring.service.ModelService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

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
    public ModelDTO findById(UUID id) {
        return mapper.toDTO(modelRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No such model with id" + id)));
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
        modelRepository.save(mapper.toEntity(model));
    }

    @Override
    public void deleteModel(ModelDTO model) {
        modelRepository.delete(mapper.toEntity(model));
    }

    @Override
    public void deleteModelById(UUID id) {
        modelRepository.deleteById(id);
    }
}

