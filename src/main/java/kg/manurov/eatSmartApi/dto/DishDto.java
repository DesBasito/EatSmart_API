package kg.manurov.eatSmartApi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record DishDto(Long id,
                      @NotNull @Size(max = 255) String name,
                      @NotNull Double calories,
                      Double protein,
                      Double fats,
                      Double carbohydrates) implements Serializable {
}