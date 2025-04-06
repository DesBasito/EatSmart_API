package kg.manurov.eatsmartapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kg.manurov.eatsmartapi.dto.reports.FullUserReportDto;
import kg.manurov.eatsmartapi.dto.reports.ReportDto;
import kg.manurov.eatsmartapi.dto.reports.UserReportDto;
import kg.manurov.eatsmartapi.exception.ErrorResponseBody;
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
            summary = "получение полного отчета за день",
            description = "Эндпоинт проверки, уложился ли пользователь в свою норму."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос прошел успешно",
                    content = @Content(schema = @Schema(implementation =  UserReportDto.class))),
            @ApiResponse(responseCode = "400", description = "Произошла ошибка при выполнении запроса",
                    content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
    })
    public ResponseEntity<UserReportDto> getUserAllowanceByDate(@PathVariable Long userId,
                                                                @RequestParam(value = "date")LocalDate date){
        return ResponseEntity.ok(reportService.getUserReport(userId,date));
    }

    @GetMapping("/user/{userId}/full")
    @Operation(
            summary = "получение полного отчета за день",
            description = "Этот эндпоинт возвращает Историю питания по дням.."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос прошел успешно",
                    content = @Content(schema = @Schema(implementation = FullUserReportDto.class))),
            @ApiResponse(responseCode = "400", description = "Произошла ошибка при выполнении запроса",
                    content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
    })
    public ResponseEntity<FullUserReportDto> getFullUserAllowanceInfoByDate(@PathVariable Long userId,
                                                                            @RequestParam(value = "date")LocalDate date){
        return ResponseEntity.ok(reportService.getFullUserInfoPerDay(userId,date));
    }
    @Operation(
            summary = "Получения записи у пользователя за текущий день",
            description = "Этот эндпоинт возвращает отчет за день: сумма всех калорий и приемов пищи.."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос прошел успешно",
                    content = @Content(schema = @Schema(implementation = ReportDto.class))),
            @ApiResponse(responseCode = "400", description = "Произошла ошибка при выполнении запроса",
                    content = @Content(schema = @Schema(implementation = ErrorResponseBody.class))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content(schema = @Schema(implementation = ErrorResponseBody.class)))
    })
    @GetMapping("/user/{userId}/today")
    public ResponseEntity<ReportDto> getUserAllowanceInfoByCurrentDate(@PathVariable Long userId){
        return ResponseEntity.ok(reportService.getReportOfCurrentDay(userId, LocalDate.now()));
    }
}
