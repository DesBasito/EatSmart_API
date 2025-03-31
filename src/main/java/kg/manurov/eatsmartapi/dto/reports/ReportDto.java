package kg.manurov.eatsmartapi.dto.reports;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kg.manurov.eatsmartapi.dto.MealDto;
import kg.manurov.eatsmartapi.util.DailyAllowanceUtil;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Builder
public record ReportDto(String username,
                        BigDecimal actualAllowance,
                        BigDecimal dailyAllowance,
                        String status,
                        List<MealDto> mealDtos ){
}