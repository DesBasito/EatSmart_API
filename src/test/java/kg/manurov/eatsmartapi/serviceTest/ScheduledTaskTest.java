package kg.manurov.eatsmartapi.serviceTest;

import kg.manurov.eatsmartapi.dto.MealDto;
import kg.manurov.eatsmartapi.dto.reports.ReportDto;
import kg.manurov.eatsmartapi.enums.*;
import kg.manurov.eatsmartapi.models.Report;
import kg.manurov.eatsmartapi.models.User;
import kg.manurov.eatsmartapi.repositories.ReportRepository;
import kg.manurov.eatsmartapi.services.impl.ReportServiceImpl;
import kg.manurov.eatsmartapi.services.interfaces.MealService;
import kg.manurov.eatsmartapi.services.interfaces.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScheduledTaskTest {

    private static final Long NEW_ID = 3L;
    private static Long checkId;
    @Mock private UserService userService;
    @Mock private MealService mealService;
    @Mock private ReportRepository repository;

    @Spy
    @InjectMocks
    private ReportServiceImpl reportService;

    @BeforeEach
    void setUp() {
        LocalDate passDay = LocalDate.now().minusDays(1);

        User user = User.builder()
                .id(1L)
                .name("John Doe")
                .age(30)
                .email("john@example.com")
                .height(175.0)
                .weight(70.0)
                .goalType(GoalType.MAINTAIN.toString())
                .gender(Gender.MALE.toString())
                .activityLevel(ActivityLevel.LIGHT.toString())
                .build();

        when(userService.getAllUsers()).thenReturn(List.of(user));
        when(userService.getUserById(user.getId())).thenReturn(user);

        MealDto dto1 = new MealDto(1L, MealTypes.DINNER.getDescription(), new ArrayList<>());
        MealDto dto2 = new MealDto(2L, MealTypes.SNACK.getDescription(), new ArrayList<>());
        List<MealDto> mealDtoList = List.of(dto1, dto2);
        when(mealService.getMealsByUserIdAndDate(user.getId(), passDay))
                .thenReturn(mealDtoList);

        when(mealService.getTotalCaloriesPerDay(eq(user.getId()), any(LocalDate.class)))
                .thenReturn(BigDecimal.valueOf(2100D));

        ReportDto reportDto = new ReportDto(
                user.getName(),
                BigDecimal.valueOf(2000D),
                BigDecimal.valueOf(2100D),
                StatusOfAllowance.DEFICIT.getDescription(),
                new ArrayList<>()
        );
        Report report = Report.builder()
                .user(user)
                .totalCalories(reportDto.actualAllowance().doubleValue())
                .date(passDay)
                .build();

        when(reportService.getReportOfCurrentDay(user.getId(), passDay)).thenReturn(reportDto);
        when(repository.save(any(Report.class))).thenAnswer(invocation -> {
            checkId = NEW_ID;
            return report;
        });
    }

    @Test
    void testChargeDailyReports() {
       reportService.chargeDailyReports();
       assertEquals(NEW_ID, checkId);
    }
}
