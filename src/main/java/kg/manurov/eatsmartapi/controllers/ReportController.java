package kg.manurov.eatsmartapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import kg.manurov.eatsmartapi.dto.reports.FullUserReportDto;
import kg.manurov.eatsmartapi.dto.reports.ReportDto;
import kg.manurov.eatsmartapi.dto.reports.UserReportDto;
import kg.manurov.eatsmartapi.services.interfaces.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/user/{userId}")
    @Operation(
            summary = "Получения записи у пользователя за текущий день",
            description = "Эндпоинт проверки, уложился ли пользователь в свою норму."
    )
    public ResponseEntity<UserReportDto> getUserAllowanceByDate(@PathVariable Long userId,
                                                                @RequestParam(value = "date")LocalDate date){
        return ResponseEntity.ok(reportService.getUserReport(userId,date));
    }

    @GetMapping("/user/{userId}/full")
    @Operation(
            summary = "получение полного отчета за день",
            description = "Этот эндпоинт возвращает Историю питания по дням.."
    )
    public ResponseEntity<FullUserReportDto> getFullUserAllowanceInfoByDate(@PathVariable Long userId,
                                                                            @RequestParam(value = "date")LocalDate date){
        return ResponseEntity.ok(reportService.getFullUserInfoPerDay(userId,date));
    }
    @Operation(
            summary = "получение отчета за день",
            description = "Этот эндпоинт возвращает отчет за день: сумма всех калорий и приемов пищи.."
    )
    @GetMapping("/user/{userId}/today")
    public ResponseEntity<ReportDto> getUserAllowanceInfoByCurrentDate(@PathVariable Long userId){
        return ResponseEntity.ok(reportService.getReportOfCurrentDay(userId));
    }
}
