package kg.manurov.eatsmartapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kg.manurov.eatsmartapi.dto.annotations.ValidMealType;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;



public record MealDto(Long id,
                      @NotBlank @ValidMealType String mealType,
                      @NotNull List<DishDto> dishes) implements Serializable {
}