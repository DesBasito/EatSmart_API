package kg.manurov.eatsmartapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.io.Serializable;

public record DishDto(Long id,
                      @NotNull @Size(max = 255) String name,
                      @NotNull @PositiveOrZero Double calories,
                      @NotNull @PositiveOrZero Double protein,
                      @NotNull @PositiveOrZero Double fats,
                      @NotNull @PositiveOrZero Double carbohydrates) implements Serializable {
}