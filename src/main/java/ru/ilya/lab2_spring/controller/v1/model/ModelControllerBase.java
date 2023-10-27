package ru.ilya.lab2_spring.controller.v1.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import ru.ilya.lab2_spring.dto.ModelDTO;
import ru.ilya.lab2_spring.service.ModelService;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import java.time.LocalDateTime;

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
            return ResponseEntity.status(HttpStatus.CREATED).body(m);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getLocalizedMessage());
        }
    }

    protected ResponseEntity<?> getModel(String modelId) {
        if ("-1".equals(modelId)) {
            return ResponseEntity.status(HttpStatus.FOUND).body(modelService.findAll());
        } else{
            return ResponseEntity.status(HttpStatus.FOUND).body(modelService.findById(modelId));
        }
    }

    protected ResponseEntity<ModelDTO> updateModel(ModelDTO modelDTO) throws IllegalArgumentRequestException {
        modelDTO.setModified(LocalDateTime.now());
        HttpStatus status = modelService.findAllByName(modelDTO.getName()).isEmpty() ? HttpStatus.CREATED : HttpStatus.ACCEPTED;
        modelService.update(modelDTO);
        return ResponseEntity.status(status).body(modelDTO);
    }

    protected ResponseEntity<?> deleteModel(String id) {
        modelService.deleteById(id);
        return ResponseEntity.ok(id);
    }
}
