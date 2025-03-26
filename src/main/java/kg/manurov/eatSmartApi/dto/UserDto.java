package kg.manurov.eatSmartApi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record UserDto(Long id, @NotNull @Size(max = 65) String name, @NotNull Integer age, @NotNull Double height,
                      @NotNull Double weight, @NotNull @Size(max = 65) String email, String goal) implements Serializable {
}