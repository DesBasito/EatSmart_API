package kg.manurov.eatsmartapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

@Schema(description = "DTO для блюдо")
public record DishDto(Long id,
                      @Schema(description = "Название блюдо")
                      @NotNull @Size(max = 255) String name,
                      @Schema(description = "Калория блюдо")
                      @NotNull @PositiveOrZero Double calories,
                      @Schema(description = "Протеины")
                      @NotNull @PositiveOrZero Double protein,
                      @Schema(description = "Жиры")
                      @NotNull @PositiveOrZero Double fats,
                      @Schema(description = "Углеводы")
                      @NotNull @PositiveOrZero Double carbohydrates) implements Serializable {
}