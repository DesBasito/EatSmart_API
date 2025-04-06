package kg.manurov.eatsmartapi.services.impl;

import kg.manurov.eatsmartapi.dto.*;
import kg.manurov.eatsmartapi.dto.reports.FullUserReportDto;
import kg.manurov.eatsmartapi.dto.reports.ReportDto;
import kg.manurov.eatsmartapi.dto.reports.UserReportDto;
import kg.manurov.eatsmartapi.mappers.ReportMapper;
import kg.manurov.eatsmartapi.models.Report;
import kg.manurov.eatsmartapi.models.User;
import kg.manurov.eatsmartapi.repositories.ReportRepository;
import kg.manurov.eatsmartapi.services.interfaces.MealService;
import kg.manurov.eatsmartapi.services.interfaces.ReportService;
import kg.manurov.eatsmartapi.services.interfaces.UserService;
import kg.manurov.eatsmartapi.util.DailyAllowanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReportServiceImpl implements ReportService {
    private final MealService mealService;
    private final UserService userService;
    private final ReportRepository repository;
    private final ReportMapper reportMapper;

    @Autowired
    public ReportServiceImpl(MealService mealService, UserService userService, ReportRepository repository, ReportMapper reportMapper) {
        this.mealService = mealService;
        this.userService = userService;
        this.repository = repository;
        this.reportMapper = reportMapper;
    }

    @Override
    @Scheduled(cron = "0 0 0 * * ?")
    public void chargeDailyReports() {
        List<User> users = userService.getAllUsers();
        LocalDate passDay = LocalDate.now().minusDays(1);
        users.forEach(u ->{
            Double totCal = getReportOfCurrentDay(u.getId(),passDay)
                    .actualAllowance()
                    .doubleValue();
            repository.save(Report.builder()
                            .totalCalories(totCal)
                            .date(passDay)
                            .user(u)
                    .build());
        });
    }

    @Override
    public UserReportDto getUserReport(Long userId, LocalDate date) {
        Report report = repository.findReportByUser_IdAndDate(userId,date).orElseThrow(NoSuchElementException::new);

        BigDecimal dailyAllowance = DailyAllowanceUtil.calculateTotalCalories(report.getUser());
        BigDecimal totalCaloriesPerDay = BigDecimal.valueOf(report.getTotalCalories()).setScale(2, RoundingMode.HALF_UP);
        String status = DailyAllowanceUtil.statusOfAllowance(dailyAllowance,totalCaloriesPerDay);
        BigDecimal actualCalories = mealService.getTotalCaloriesPerDay(report.getUser().getId(), date);
        return reportMapper.fromEntityWithContext(report, dailyAllowance, actualCalories, status);
    }

    @Override
    public FullUserReportDto getFullUserInfoPerDay(Long userId, LocalDate date){
        Report report = repository.findReportByUser_IdAndDate(userId,date).orElseThrow(NoSuchElementException::new);
        List<MealDto> meals = mealService.getMealsByUserIdAndDate(userId,date);
        return FullUserReportDto.builder()
                .username(report.getUser().getName())
                .date(date)
                .mealDto(meals)
                .build();
    }

    @Override
    public ReportDto getReportOfCurrentDay(Long userId, LocalDate date){
        List<MealDto> meals = mealService.getMealsByUserIdAndDate(userId,date);
        if (meals == null) return null;

        User user = userService.getUserById(userId);
        BigDecimal totalCaloriesForToday = mealService.getTotalCaloriesPerDay(userId,LocalDate.now());
        BigDecimal dailyAllowance = DailyAllowanceUtil.calculateTotalCalories(user);
        String status = DailyAllowanceUtil.statusOfAllowance(dailyAllowance,totalCaloriesForToday);
        return ReportDto.builder()
                .username(user.getName())
                .mealDtos(meals)
                .actualAllowance(totalCaloriesForToday)
                .dailyAllowance(dailyAllowance)
                .status(status)
                .build();
    }
}
