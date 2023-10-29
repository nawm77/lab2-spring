package ru.ilya.lab2_spring.controller.v1.userRole;

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
import ru.ilya.lab2_spring.dto.UserRoleDTO;
import ru.ilya.lab2_spring.model.api.ApiErrorResponse;
import ru.ilya.lab2_spring.util.exception.IllegalArgumentRequestException;

import static ru.ilya.lab2_spring.model.api.ApiConstants.*;

public interface UserRoleController {
    @Operation(summary = "Создание новой роли")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Роль успешно создана",
                    content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = UserRoleDTO.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные",
                    content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Роль уже существует",
                    content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @RequestMapping(value = USER_ROLE_API_V1_PATH + "/**",
            produces = {JSON_TYPE},
            consumes = {JSON_TYPE},
            method = RequestMethod.POST)
    ResponseEntity<UserRoleDTO> createUserRole(@RequestBody UserRoleDTO userRoleDTO) throws IllegalArgumentRequestException;

    @Operation(summary = "Получение списка всех ролей / одной роли с возможностью получить список пользователей по ролям")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Роль успешно найдена",
                    content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = UserRoleDTO.class))),
            @ApiResponse(responseCode = "404", description = "Роль не найдена")
    })
    @RequestMapping(value = USER_ROLE_API_V1_PATH + "/**",
            produces = {JSON_TYPE},
            consumes = {JSON_TYPE},
            method = RequestMethod.GET)
    ResponseEntity<?> getUserRole(
            @Parameter(in = ParameterIn.QUERY, description = "ID роли", schema = @Schema(defaultValue = "-1"))
            @RequestParam(value = "userId", required = false, defaultValue = "-1") String roleId,
            @Parameter(in = ParameterIn.QUERY, description = "Отображение ролей вместе с пользователями", schema = @Schema(defaultValue = "false"))
            @RequestParam(value = "withUsers", required = false, defaultValue = "false") Boolean withUsers
    );

    @Operation(summary = "Редактирование роли. Если роль отсутствует, она будет создана")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Роль создана",
                    content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = UserRoleDTO.class))),
            @ApiResponse(responseCode = "202", description = "Роль изменена",
                    content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = UserRoleDTO.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные",
                    content = @Content(mediaType = JSON_TYPE, schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @RequestMapping(value = USER_ROLE_API_V1_PATH + "/**",
            produces = {JSON_TYPE},
            consumes = {JSON_TYPE},
            method = RequestMethod.PUT)
    ResponseEntity<UserRoleDTO> updateUserRole(@RequestBody UserRoleDTO userRoleDTO) throws IllegalArgumentRequestException;

    @Operation(summary = "Удаление роли")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Роль удалена",
                    content = @Content(mediaType = JSON_TYPE)),
            @ApiResponse(responseCode = "404", description = "Роль не найдена",
                    content = @Content(mediaType = JSON_TYPE))
    })
    @RequestMapping(value = USER_ROLE_API_V1_PATH + "/**",
            produces = {JSON_TYPE},
            consumes = {ALL_TYPE},
            method = RequestMethod.DELETE)
    ResponseEntity<?> deleteUserRole(
            @Parameter(in = ParameterIn.QUERY, description = "ID роли", schema = @Schema())
            @RequestParam(value = "roleId") String roleId
    );
}
