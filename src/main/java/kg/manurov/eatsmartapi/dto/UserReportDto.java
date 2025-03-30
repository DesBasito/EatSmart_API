package kg.manurov.eatsmartapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UserReportDto(
        String username,
        LocalDate date,
        BigDecimal dailyAllowance,
        String status
) {
}
