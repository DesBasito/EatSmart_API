package kg.manurov.eatsmartapi.services.interfaces;

import kg.manurov.eatsmartapi.models.Meal;

import java.time.LocalDate;
import java.util.List;

public interface MealService {
    List<Meal> getMealByUserIdAndDate(Long id, LocalDate date);
}
