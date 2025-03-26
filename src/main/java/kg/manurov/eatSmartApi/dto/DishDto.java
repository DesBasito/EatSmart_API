package kg.manurov.eatSmartApi.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

@Setter
@Getter
@Builder
public class DishDto{
    private Long id;
    @NotNull
    @Size(max = 255)
    @NotEmpty
    @NotBlank
    private String name;
    @NotNull
    @Positive
    private Double calories;
    @NotNull
    @Positive
    private Double protein;
    @NotNull
    @Positive
    private Double fats;
    @NotNull
    @Positive
    private Double carbohydrates;
}