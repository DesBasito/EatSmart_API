package kg.manurov.eatsmartapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kg.manurov.eatsmartapi.dto.DishDto;
import kg.manurov.eatsmartapi.dto.MealDto;
import kg.manurov.eatsmartapi.exception.ErrorResponseBody;
import kg.manurov.eatsmartapi.services.interfaces.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dishes")
@RequiredArgsConstructor
public class DishController {
    private final DishService service;

    @PostMapping()
    @Operation(summary = "Добавления пищи",
            description = "Эндпоинт для создание пищи. Возвращает сущности DishDto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос прошел успешно",
                    content = @Content(schema = @Schema(implementation = DishDto.class))),
            @ApiResponse(responseCode = "400", description = "Произошла ошибка при выполнении запроса",
                    content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
    })
    public ResponseEntity<DishDto> create(@RequestBody @Validated @Parameter(description = "Блюдо") DishDto dishDto){
        return ResponseEntity.ok(service.create(dishDto));
    }
}
