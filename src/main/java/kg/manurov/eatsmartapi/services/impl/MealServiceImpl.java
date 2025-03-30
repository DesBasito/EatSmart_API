package kg.manurov.eatsmartapi.services.impl;

import kg.manurov.eatsmartapi.models.Meal;
import kg.manurov.eatsmartapi.repositories.MealRepository;
import kg.manurov.eatsmartapi.services.interfaces.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;
    @Override
    public List<Meal> getMealByUserIdAndDate(Long userId, LocalDate date) {
        return mealRepository.getMealsByUser_IdAndDate(userId,date);
    }
}
