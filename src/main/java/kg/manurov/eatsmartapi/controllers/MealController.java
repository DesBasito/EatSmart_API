package kg.manurov.eatsmartapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import kg.manurov.eatsmartapi.dto.MealDto;
import kg.manurov.eatsmartapi.services.interfaces.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<MealDto> create(@RequestBody @Validated MealDto mealDto,
                                          @PathVariable Long userId){
        return ResponseEntity.ok(mealService.create(mealDto,userId));
    }
}
