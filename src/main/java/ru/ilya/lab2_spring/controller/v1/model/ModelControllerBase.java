package ru.ilya.lab2_spring.controller.v1.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import ru.ilya.lab2_spring.dto.ModelDTO;
import ru.ilya.lab2_spring.service.ModelService;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import java.util.List;

@Slf4j
public abstract class ModelControllerBase {
    private final ModelService modelService;

    protected ModelControllerBase(ModelService modelService) {
        this.modelService = modelService;
    }


    protected ResponseEntity<ModelDTO> createModel(ModelDTO modelDTO) throws IllegalArgumentRequestException {
    }

    protected ResponseEntity<List<ModelDTO>> getModel(String modelId) {
    }

    protected ResponseEntity<ModelDTO> updateModel(ModelDTO modelDTO) throws IllegalArgumentRequestException {
    }

    protected ResponseEntity<?> deleteModel(String id) {
    }
}
