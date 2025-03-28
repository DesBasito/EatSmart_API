package kg.manurov.eatSmartApi.services.interfaces;

import org.springframework.scheduling.annotation.Scheduled;

public interface ReportService {
    @Scheduled(cron = "0 0 0 1 * ?")
    void chargeDailyReports();
}
