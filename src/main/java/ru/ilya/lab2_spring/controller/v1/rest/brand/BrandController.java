package ru.ilya.lab2_spring.controller.v1.rest.brand;

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
import ru.ilya.lab2_spring.dto.BrandDTO;
import ru.ilya.lab2_spring.model.api.ApiErrorResponse;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import static ru.ilya.lab2_spring.model.api.ApiConstants.*;

public interface BrandController {
    @Operation(summary = "Создание нового бренда")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Бренд успешно создан",
                    content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = BrandDTO.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные",
                    content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Бренд уже существует",
                    content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @RequestMapping(value = BRAND_API_V1_PATH + "/**",
    produces = {JSON_TYPE},
    consumes = {JSON_TYPE},
    method = RequestMethod.POST)
    ResponseEntity<BrandDTO> createBrand(@RequestBody BrandDTO brandDTO) throws IllegalArgumentRequestException;

    @Operation(summary = "Получение списка всех брендов / одного бренда с возможностью получить список моделей бренда или брендов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Бренд успешно найден",
                    content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = BrandDTO.class))),
            @ApiResponse(responseCode = "404", description = "Бренд не найден")
    })
    @RequestMapping(value = BRAND_API_V1_PATH + "/**",
    produces = {JSON_TYPE},
    consumes = {JSON_TYPE},
    method = RequestMethod.GET)
    ResponseEntity<?> getBrand(
            @Parameter(in = ParameterIn.QUERY, description = "ID бренда", schema = @Schema(defaultValue = "-1"))
            @RequestParam(value = "brandId", required = false, defaultValue = "-1") String brandId,
            @Parameter(in = ParameterIn.QUERY, description = "Отображение брендов вместе с моделями", schema = @Schema(defaultValue = "false"))
            @RequestParam(value = "withModels", required = false, defaultValue = "false") Boolean withModels
    );

    @Operation(summary = "Редактирование бренда. Если бренд отсутствует, он будет создан")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Бренд создан",
                    content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = BrandDTO.class))),
            @ApiResponse(responseCode = "202", description = "Бренд изменен",
                    content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = BrandDTO.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные",
                    content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @RequestMapping(value = BRAND_API_V1_PATH + "/**",
    produces = {JSON_TYPE},
    consumes = {JSON_TYPE},
    method = RequestMethod.PUT)
    ResponseEntity<BrandDTO> updateBrand(@RequestBody BrandDTO brandDTO) throws IllegalArgumentRequestException;

    @Operation(summary = "Удаление бренда")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Бренд удален",
                    content = @Content(mediaType = JSON_TYPE)),
            @ApiResponse(responseCode = "404", description = "Бренд не найден",
                    content = @Content(mediaType = JSON_TYPE))
    })
    @RequestMapping(value = BRAND_API_V1_PATH + "/**",
    produces = {JSON_TYPE},
    consumes = {ALL_TYPE},
    method = RequestMethod.DELETE)
    ResponseEntity<?> deleteBrand(
            @Parameter(in = ParameterIn.QUERY, description = "ID бренда", schema = @Schema())
            @RequestParam(value = "brandId") String id
    );
}
