package ru.ilya.lab2_spring.controller.v1.offer;

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
import ru.ilya.lab2_spring.dto.OfferDTO;
import ru.ilya.lab2_spring.model.api.ApiErrorResponse;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import static ru.ilya.lab2_spring.model.api.ApiConstants.*;

public interface OfferController {
    @Operation(summary = "Создание нового заказа")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Заказ успешно создан", content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = OfferDTO.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные", content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Заказ уже существует", content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @RequestMapping(value = OFFER_API_V1_PATH + "/**",
            produces = {JSON_TYPE},
            consumes = {JSON_TYPE},
            method = RequestMethod.POST)
    ResponseEntity<OfferDTO> createOffer(@RequestBody OfferDTO offerDTO) throws IllegalArgumentRequestException;

    @Operation(summary = "Получение списка всех заказов / одного заказа")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Заказ успешно найден", content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = OfferDTO.class))),
            @ApiResponse(responseCode = "404", description = "Заказ не найден")
    })
    @RequestMapping(value = OFFER_API_V1_PATH + "/**",
            produces = {JSON_TYPE},
            consumes = {JSON_TYPE},
            method = RequestMethod.GET)
    ResponseEntity<?> getOffer(
            @Parameter(in = ParameterIn.QUERY, description = "ID заказа", schema = @Schema(defaultValue = "-1")) @RequestParam(value = "offerId", required = false, defaultValue = "-1") String offerId
    );

    @Operation(summary = "Редактирование заказа. Если заказ отсутствует, он будет создан")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Заказ создан", content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = OfferDTO.class))),
            @ApiResponse(responseCode = "202", description = "Заказ изменен", content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = OfferDTO.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные", content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @RequestMapping(value = OFFER_API_V1_PATH + "/**",
            produces = {JSON_TYPE},
            consumes = {JSON_TYPE},
            method = RequestMethod.PUT)
    ResponseEntity<OfferDTO> updateOffer(@RequestBody OfferDTO offerDTO) throws IllegalArgumentRequestException;

    @Operation(summary = "Удаление заказа")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Заказ удален", content = @Content(mediaType = JSON_TYPE)),
            @ApiResponse(responseCode = "404", description = "Заказ не найден", content = @Content(mediaType = JSON_TYPE))
    })
    @RequestMapping(value = OFFER_API_V1_PATH + "/**",
            produces = {JSON_TYPE},
            consumes = {ALL_TYPE},
            method = RequestMethod.DELETE)
    ResponseEntity<?> deleteOffer(
            @Parameter(in = ParameterIn.QUERY, description = "ID заказа", schema = @Schema()) @RequestParam(value = "offerId") String id
    );
}
