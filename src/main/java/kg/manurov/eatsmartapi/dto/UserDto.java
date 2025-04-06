package kg.manurov.eatsmartapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import kg.manurov.eatsmartapi.dto.annotations.ValidUserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ValidUserDto
@Schema(description = "DTO для пользователя")
public record UserDto(Long id,
                      @NotBlank
                      @Size(max = 65)
                      @Schema(description = "Имя пользователя", example = "Иван Петров")
                      String name,

                      @NotNull
                      @PositiveOrZero(message = "Введите правильный возраст")
                      @Schema(description = "Возраст пользователя (0 и выше)", example = "30", minimum = "0")
                      Integer age,

                      @NotNull
                      @Min(value = 40, message = "Введите правильный рост")
                      @Schema(description = "Рост пользователя в сантиметрах (не менее 40 см)", example = "175", minimum = "40")
                      Double height,

                      @NotNull
                      @Min(value = 40, message = "Введите правильный вес")
                      @Schema(description = "Вес пользователя в килограммах (не менее 40 кг)", example = "70", minimum = "40")
                      Double weight,

                      @NotBlank
                      @Email
                      @Size(max = 65)
                      @Schema(description = "Email пользователя", example = "user@example.com", maxLength = 65)
                      String email,

                      @NotBlank
                      @Schema(description = "Уровень активности пользователя. Например: Минимальная, Легкая, Средняя, Высокая, Спортсмен", example = "Средняя")
                      String activityLevel,

                      @NotBlank
                      @Schema(description = "Пол пользователя. Например: Мужчина или Женщина", example = "Женщина")
                      String gender,

                      @NotBlank
                      @Schema(description = "Цель пользователя. Например: похудение, набор массы, поддержание", example = "поддержание")
                      String goalType){
}