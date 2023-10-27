package ru.ilya.lab2_spring.controller.v1.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.ilya.lab2_spring.dto.ModelDTO;
import ru.ilya.lab2_spring.service.ModelService;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import java.util.List;

@RestController
@Slf4j
public class ModelControllerImpl extends ModelControllerBase implements ModelController{
    @Autowired
    public ModelControllerImpl(ModelService modelService) {
        super(modelService);
    }

    @Override
    public ResponseEntity<ModelDTO> createModel(ModelDTO modelDTO) throws IllegalArgumentRequestException {
        return super.createModel(modelDTO);
    }

    @Override
    public ResponseEntity<List<ModelDTO>> getModel(String modelId) {
        return super.getModel(modelId);
    }

    @Override
    public ResponseEntity<ModelDTO> updateModel(ModelDTO modelDTO) throws IllegalArgumentRequestException {
        return super.updateModel(modelDTO);
    }

    @Override
    public ResponseEntity<?> deleteModel(String id) {
        return super.deleteModel(id);
    }
}
