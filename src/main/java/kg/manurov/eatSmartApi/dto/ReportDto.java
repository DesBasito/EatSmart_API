package kg.manurov.eatSmartApi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;


public record ReportDto(Long id, @NotNull Double totalCalories, @NotNull LocalDate date, @NotBlank String username) implements Serializable {
}