package ru.ilya.lab2_spring.controller.v1.model;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ilya.lab2_spring.dto.ModelDTO;
import ru.ilya.lab2_spring.model.api.ApiErrorResponse;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import java.util.List;

import static ru.ilya.lab2_spring.model.api.ApiConstants.JSON_TYPE;
import static ru.ilya.lab2_spring.model.api.ApiConstants.MODEL_API_V1_PATH;

public interface ModelController {
    @Operation(summary = "Создание новой модели")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Модель успешно создана", content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = ModelDTO.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные", content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Модель уже существует", content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @RequestMapping(value = MODEL_API_V1_PATH + "/**",
    produces = {JSON_TYPE},
    consumes = {JSON_TYPE},
    method = RequestMethod.POST)
    ResponseEntity<ModelDTO> createModel(@RequestBody ModelDTO modelDTO) throws IllegalArgumentRequestException;

    @Operation(summary = "Получение списка всех моделей / одной модели")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Модель успешно найдена", content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = ModelDTO.class))),
            @ApiResponse(responseCode = "404", description = "Модель не найдена")
    })
    @RequestMapping(value = MODEL_API_V1_PATH + "/**",
            produces = {JSON_TYPE},
            consumes = {JSON_TYPE},
            method = RequestMethod.GET)
    ResponseEntity<List<ModelDTO>> getModel(
            @Parameter(in = ParameterIn.QUERY, description = "ID модели", schema = @Schema(defaultValue = "-1")) @RequestParam(value = "modelId", required = false, defaultValue = "-1") String modelId
    );

    ResponseEntity<ModelDTO> updateModel(ModelDTO modelDTO) throws IllegalArgumentRequestException;
    ResponseEntity<?> deleteModel(String id);
}
