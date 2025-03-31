package kg.manurov.eatsmartapi.services.interfaces;

import kg.manurov.eatsmartapi.dto.MealDto;
import kg.manurov.eatsmartapi.models.Meal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface MealService {
    List<MealDto> getMealsByUserIdAndDate(Long id, LocalDate date);

    BigDecimal getTotalCaloriesPerDay(Long id, LocalDate date);

    MealDto create(MealDto mealDto,Long userId);

}
