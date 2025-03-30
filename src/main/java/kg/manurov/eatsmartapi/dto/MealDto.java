package kg.manurov.eatsmartapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kg.manurov.eatsmartapi.dto.annotations.ValidMealDto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@ValidMealDto
public record MealDto(Long id,
                      @NotBlank LocalDate date,
                      @NotBlank String mealType,
                      @NotNull List<DishDto> dishes) implements Serializable {
}