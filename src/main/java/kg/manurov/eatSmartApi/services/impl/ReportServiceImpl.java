package kg.manurov.eatSmartApi.services.impl;

import kg.manurov.eatSmartApi.services.interfaces.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    @Override
    @Scheduled(cron = "0 0 0 1 * ?")
    public void chargeDailyReports() {

    }
}
