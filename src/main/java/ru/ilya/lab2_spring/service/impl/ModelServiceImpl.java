package ru.ilya.lab2_spring.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.ilya.lab2_spring.dto.ModelDTO;
import ru.ilya.lab2_spring.mapper.Mapper;
import ru.ilya.lab2_spring.repository.ModelRepository;
import ru.ilya.lab2_spring.service.ModelService;
import ru.ilya.lab2_spring.service.util.ValidationUtil;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class ModelServiceImpl implements ModelService {
    private ModelRepository modelRepository;
    private final Mapper mapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public ModelServiceImpl(Mapper mapper, ValidationUtil validationUtil) {
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }

    @Autowired
    public void setModelRepository(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public ModelDTO findById(String id) {
        return mapper.toDTO(modelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such model with id" + id)));
    }

    @Override
    public void deleteById(String id) {
        try {
            modelRepository.deleteById(id);
            log.info("Delete model with id {}", id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getLocalizedMessage());
        }
    }

    @Override
    public ModelDTO update(ModelDTO model) throws IllegalArgumentRequestException {
        if (modelRepository.findById(model.getId()).isPresent()) {
            log.info("Update model {}", model);
        }
        return saveOrUpdate(model);
    }

    @Override
    public List<ModelDTO> findAll() {
        return modelRepository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public List<ModelDTO> findAllByName(String name) {
        return modelRepository.findAllByName(name).stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public ModelDTO save(ModelDTO model) throws IllegalArgumentRequestException {
        ModelDTO modelDTO = saveOrUpdate(model);
        log.info("Create model {} with id {} and name {}", modelDTO, modelDTO.getId(), modelDTO.getName());
        return modelDTO;
    }

    private ModelDTO saveOrUpdate(ModelDTO model) throws IllegalArgumentRequestException {
        validationUtil.validateDTO(model);
        try {
            return mapper.toDTO(modelRepository.save(mapper.toEntity(model)));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getLocalizedMessage());
        }
    }
}

