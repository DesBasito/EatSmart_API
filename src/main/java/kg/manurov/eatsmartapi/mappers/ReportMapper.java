package kg.manurov.eatsmartapi.mappers;

import kg.manurov.eatsmartapi.dto.reports.ReportDto;
import kg.manurov.eatsmartapi.dto.reports.UserReportDto;
import kg.manurov.eatsmartapi.models.Report;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(componentModel = "spring",uses = {UserMapper.class, MealMapper.class})
public interface ReportMapper {

    @Mapping(target = "username", source = "user.name")
    @Mapping(target = "dailyAllowance", ignore = true)
    @Mapping(target = "actualAllowance", ignore = true)
    @Mapping(target = "status", ignore = true)
    UserReportDto fromEntityToUserReport(Report report);

    default UserReportDto fromEntityWithContext(Report report,
                                                @Context BigDecimal dailyAllowance,
                                                @Context BigDecimal actualAllowance,
                                                @Context String status) {
        UserReportDto dto = fromEntityToUserReport(report);
        return new UserReportDto(dto.username(), report.getDate(), dailyAllowance, actualAllowance, status);
    }
}

