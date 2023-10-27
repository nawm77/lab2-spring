package ru.ilya.lab2_spring.controller.v1.brand;

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

import static ru.ilya.lab2_spring.model.api.ApiConstants.BRAND_API_V1_PATH;
import static ru.ilya.lab2_spring.model.api.ApiConstants.JSON_TYPE;

public interface BrandController {
    @Operation(summary = "Создание нового бренда")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Бренд успешно создан", content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = BrandDTO.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные", content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Бренд уже существует", content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @RequestMapping(value = BRAND_API_V1_PATH + "/**",
    produces = {JSON_TYPE},
    consumes = {JSON_TYPE},
    method = RequestMethod.POST)
    ResponseEntity<BrandDTO> createBrand(@RequestBody BrandDTO brandDTO) throws IllegalArgumentRequestException;

    @Operation(summary = "Получение списка всех брендов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Бренд успешно найден", content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = BrandDTO.class))),
            @ApiResponse(responseCode = "204", description = "Бренд не найден")
    })
    @RequestMapping(value = BRAND_API_V1_PATH + "/**",
    produces = {JSON_TYPE},
    consumes = {JSON_TYPE},
    method = RequestMethod.GET)
    ResponseEntity<BrandDTO> getBrand(@Parameter(in = ParameterIn.QUERY, description = "ID бренда", schema = @Schema(defaultValue = "-1")) @RequestParam(value = "brandId", required = false, defaultValue = "-1") String brandId);
}
