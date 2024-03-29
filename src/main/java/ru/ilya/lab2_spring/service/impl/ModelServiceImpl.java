package ru.ilya.lab2_spring.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.ilya.lab2_spring.dto.BrandDTO;
import ru.ilya.lab2_spring.dto.ModelDTO;
import ru.ilya.lab2_spring.mapper.Mapper;
import ru.ilya.lab2_spring.model.Model;
import ru.ilya.lab2_spring.repository.ModelRepository;
import ru.ilya.lab2_spring.service.BrandService;
import ru.ilya.lab2_spring.service.ModelService;
import ru.ilya.lab2_spring.service.util.Constants;
import ru.ilya.lab2_spring.service.util.ValidationUtil;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static ru.ilya.lab2_spring.service.util.Constants.REDIS_MODELS_CACHE_NAME;

@Service
@Slf4j
@EnableCaching
public class ModelServiceImpl implements ModelService {
    private ModelRepository modelRepository;
    private final Mapper mapper;
    private final ValidationUtil validationUtil;
    private final BrandService brandService;

    @Autowired
    public ModelServiceImpl(Mapper mapper, ValidationUtil validationUtil, BrandService brandService) {
        this.mapper = mapper;
        this.validationUtil = validationUtil;
        this.brandService = brandService;
    }

    @Autowired
    public void setModelRepository(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    @Cacheable(key = "#id", value = REDIS_MODELS_CACHE_NAME)
    public ModelDTO findById(String id) {
        return mapper.toDTO(modelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such model with id" + id)));
    }

    @Override
    @CacheEvict(key = "#id", value = REDIS_MODELS_CACHE_NAME)
    public void deleteById(String id) {
        try {
            modelRepository.deleteById(id);
            log.info("Delete model with id {}", id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getLocalizedMessage());
        }
    }

    @Override
    @Transactional
    @CachePut(key = "#model.id", value = REDIS_MODELS_CACHE_NAME)
    public ModelDTO update(ModelDTO model) throws IllegalArgumentRequestException {
        if (!modelRepository.findAllByName(model.getName()).isEmpty()) {
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
//    @Cacheable(value = REDIS_MODELS_CACHE_NAME, key = "#model.name")
    public ModelDTO save(ModelDTO model) throws IllegalArgumentRequestException {
        ModelDTO modelDTO = saveOrUpdate(model);
        log.info("Create model {} with id {} and name {}", modelDTO, modelDTO.getId(), modelDTO.getName());
        return modelDTO;
    }

    private ModelDTO saveOrUpdate(ModelDTO model) throws IllegalArgumentRequestException {
        model.setModified(LocalDateTime.now().toString());
        BrandDTO brandDTO = model.getBrandDTO();
        try {
            brandDTO = brandService.findAllByName(model.getBrandDTO().getName()).stream().findFirst().get();
            log.info("Found brandDTO {}", brandDTO);
        } catch (Exception e) {
            log.info("BrandDTO not found. Saving BrandDTO {}", brandDTO);
            brandDTO = brandService.save(model.getBrandDTO());
        }
        Model modelEntity = mapper.toEntity(model);
        modelEntity.setBrand(mapper.toEntity(brandDTO));
        validationUtil.validateDTO(model);
        try {
            return mapper.toDTO(modelRepository.save(modelEntity));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getLocalizedMessage());
        }
    }
}

