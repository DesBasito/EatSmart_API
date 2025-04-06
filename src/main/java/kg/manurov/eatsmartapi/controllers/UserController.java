package kg.manurov.eatsmartapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import kg.manurov.eatsmartapi.dto.DishDto;
import kg.manurov.eatsmartapi.dto.UserDto;
import kg.manurov.eatsmartapi.exception.ErrorResponseBody;
import kg.manurov.eatsmartapi.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @Operation(
            summary = "Создание пользователя",
            description = "Этот эндпоинт создает нового пользователя и возвращает его данные в виде `UserDto`."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос прошел успешно",
                    content = @Content(schema = @Schema(implementation = DishDto.class))),
            @ApiResponse(responseCode = "400", description = "Произошла ошибка при выполнении запроса",
                    content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
    })
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto userDto){
        UserDto user = userService.create(userDto);
        return ResponseEntity.ok(user);
    }
}
