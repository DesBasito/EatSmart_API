package kg.manurov.eatsmartapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import kg.manurov.eatsmartapi.dto.DishDto;
import kg.manurov.eatsmartapi.dto.MealDto;
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
    public ResponseEntity<DishDto> create(@RequestBody @Validated DishDto dishDto){
        return ResponseEntity.ok(service.create(dishDto));
    }
}
