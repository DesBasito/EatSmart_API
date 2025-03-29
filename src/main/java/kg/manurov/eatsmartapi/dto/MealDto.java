package kg.manurov.eatsmartapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


public record MealDto(Long id,
                      @NotBlank LocalDate date,
                      @NotBlank String mealType,
                      @NotNull List<DishDto> dishes) implements Serializable {
}