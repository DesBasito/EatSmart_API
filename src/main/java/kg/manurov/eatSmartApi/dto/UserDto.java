package kg.manurov.eatSmartApi.dto;

import jakarta.validation.constraints.*;

import java.io.Serializable;

public record UserDto(Long id,
                      @NotBlank @Size(max = 65) String name,
                      @NotNull @PositiveOrZero(message = "Введите правильный возраст") Integer age,
                      @NotNull @Min(value = 40, message = "Введите правильный рост") Double height,
                      @NotNull @Min(value = 40, message = "Введите правильный рост") Double weight,
                      @NotBlank @Email @Size(max = 65) String email,
                      @NotBlank String goal){
}