package kg.manurov.eatsmartapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kg.manurov.eatsmartapi.dto.MealDto;
import kg.manurov.eatsmartapi.exception.ErrorResponseBody;
import kg.manurov.eatsmartapi.services.interfaces.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meals")
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;

    @PostMapping("/{userId}")
    @Operation(summary = "Добавления приема пищи",
            description = "Эндпоинт для создание приема пищи. Возвращает сущность данного объекта и принимает айди с uri id пользователя.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос прошел успешно",
                    content = @Content(schema = @Schema(implementation = MealDto.class))),
            @ApiResponse(responseCode = "400", description = "Произошла ошибка при выполнении запроса",
                    content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
    })
    public ResponseEntity<MealDto> create(@RequestBody @Validated MealDto mealDto,
                                          @PathVariable Long userId){
        return ResponseEntity.ok(mealService.create(mealDto,userId));
    }
}
