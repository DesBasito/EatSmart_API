package kg.manurov.eatsmartapi.dto.reports;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record UserReportDto(
        String username,
        LocalDate date,
        BigDecimal dailyAllowance,
        BigDecimal actualAllowance,
        String status
) {
}
