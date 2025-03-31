package kg.manurov.eatsmartapi.services.interfaces;

import kg.manurov.eatsmartapi.dto.reports.FullUserReportDto;
import kg.manurov.eatsmartapi.dto.reports.ReportDto;
import kg.manurov.eatsmartapi.dto.reports.UserReportDto;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;

public interface ReportService {
    @Scheduled(cron = "0 0 0 1 * ?")
    void chargeDailyReports();

    UserReportDto getUserReport(Long id, LocalDate date);

    FullUserReportDto getFullUserInfoPerDay(Long userId, LocalDate date);

    ReportDto getReportOfCurrentDay(Long userId,LocalDate date);
}
