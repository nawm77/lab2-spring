package ru.ilya.lab2_spring.controller.v1.user;

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
import ru.ilya.lab2_spring.dto.UserDTO;
import ru.ilya.lab2_spring.model.api.ApiErrorResponse;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import static ru.ilya.lab2_spring.model.api.ApiConstants.*;

public interface UserController {
    @Operation(summary = "Создание нового пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Пользователь успешно создан",
                    content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные",
                    content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Пользователь уже существует",
                    content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @RequestMapping(value = USER_API_V1_PATH + "/**",
            produces = {JSON_TYPE},
            consumes = {JSON_TYPE},
            method = RequestMethod.POST)
    ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) throws IllegalArgumentRequestException;

    @Operation(summary = "Получение списка всех пользователей / одного пользователя с возможностью получить список заказов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Пользователь успешно найден",
                    content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @RequestMapping(value = USER_API_V1_PATH + "/**",
            produces = {JSON_TYPE},
            consumes = {JSON_TYPE},
            method = RequestMethod.GET)
    ResponseEntity<?> getUser(
            @Parameter(in = ParameterIn.QUERY, description = "ID пользователя", schema = @Schema(defaultValue = "-1"))
            @RequestParam(value = "userId", required = false, defaultValue = "-1") String userId,
            @Parameter(in = ParameterIn.QUERY, description = "Отображение пользователей вместе с заказами", schema = @Schema(defaultValue = "false"))
            @RequestParam(value = "withOffers", required = false, defaultValue = "false") Boolean withOffers
    );

    @Operation(summary = "Редактирование пользователя. Если пользователь отсутствует, он будет создан")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Пользователь создан",
                    content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "202", description = "Пользователь изменен",
                    content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные",
                    content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @RequestMapping(value = USER_API_V1_PATH + "/**",
            produces = {JSON_TYPE},
            consumes = {JSON_TYPE},
            method = RequestMethod.PUT)
    ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) throws IllegalArgumentRequestException;

    @Operation(summary = "Удаление пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь удален",
                    content = @Content(mediaType = JSON_TYPE)),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден",
                    content = @Content(mediaType = JSON_TYPE))
    })
    @RequestMapping(value = USER_API_V1_PATH + "/**",
            produces = {JSON_TYPE},
            consumes = {ALL_TYPE},
            method = RequestMethod.DELETE)
    ResponseEntity<?> deleteUser(
            @Parameter(in = ParameterIn.QUERY, description = "ID пользователя", schema = @Schema())
            @RequestParam(value = "userId") String id
    );
}
