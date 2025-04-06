package kg.manurov.eatsmartapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kg.manurov.eatsmartapi.dto.annotations.ValidMealType;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;



public record MealDto(Long id,
                      @Schema(
                              description = "Тип приёма пищи. Например: завтрак, обед, ужин, перекус",
                              example = "LUNCH"
                      )
                      @NotBlank @ValidMealType String mealType,
                      @Schema(
                              description = "Список блюд, входящих в приём пищи",
                              implementation = DishDto.class
                      )
                      @NotNull List<DishDto> dishes) implements Serializable {
}