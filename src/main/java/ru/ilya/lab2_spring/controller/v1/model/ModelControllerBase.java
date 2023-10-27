package ru.ilya.lab2_spring.controller.v1.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import ru.ilya.lab2_spring.dto.ModelDTO;
import ru.ilya.lab2_spring.service.ModelService;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public abstract class ModelControllerBase {
    private final ModelService modelService;

    protected ModelControllerBase(ModelService modelService) {
        this.modelService = modelService;
    }

    protected ResponseEntity<ModelDTO> createModel(ModelDTO modelDTO) throws IllegalArgumentRequestException {
        modelDTO.setModified(LocalDateTime.now());
        ModelDTO m;
        try {
            m = modelService.save(modelDTO);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getLocalizedMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(modelDTO);
    }

    protected ResponseEntity<List<ModelDTO>> getModel(String modelId) {
        if ("-1".equals(modelId)) {

        }
    }

    protected ResponseEntity<ModelDTO> updateModel(ModelDTO modelDTO) throws IllegalArgumentRequestException {
    }

    protected ResponseEntity<?> deleteModel(String id) {
    }
}
