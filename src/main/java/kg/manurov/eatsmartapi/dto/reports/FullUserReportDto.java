package kg.manurov.eatsmartapi.dto.reports;

import kg.manurov.eatsmartapi.dto.MealDto;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
public record FullUserReportDto(
        String username,
        LocalDate date,
        List<MealDto> mealDto
) {}
