package kg.manurov.eatsmartapi.services.impl;

import kg.manurov.eatsmartapi.dto.MealDto;
import kg.manurov.eatsmartapi.mappers.MealMapper;
import kg.manurov.eatsmartapi.models.Dish;
import kg.manurov.eatsmartapi.models.Meal;
import kg.manurov.eatsmartapi.models.User;
import kg.manurov.eatsmartapi.repositories.MealRepository;
import kg.manurov.eatsmartapi.services.interfaces.MealService;
import kg.manurov.eatsmartapi.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;
    private final MealMapper mealMapper;
    private final UserService userService;

    @Autowired
    public MealServiceImpl(MealRepository mealRepository, MealMapper mealMapper, UserService userService) {
        this.mealRepository = mealRepository;
        this.mealMapper = mealMapper;
        this.userService = userService;
    }

    @Override
    public List<MealDto> getMealsByUserIdAndDate(Long userId, LocalDate date) {
        return mealRepository.getMealsByUser_IdAndDate(userId, date)
                .stream()
                .map(mealMapper::toDto)
                .toList();
    }

    @Override
    public BigDecimal getTotalCaloriesPerDay(Long userId, LocalDate date) {
        List<Meal> meals = mealRepository.getMealsByUser_IdAndDate(userId,date);
        double totalCaloriesPerDay = meals.stream()
                .flatMap(meal -> meal.getDishes().stream())
                .mapToDouble(Dish::getCalories)
                .sum();
        return BigDecimal.valueOf(totalCaloriesPerDay).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public MealDto create(MealDto mealDto, Long userId) {
        User user = userService.getUserById(userId);
        Meal meal = mealMapper.toEntity(mealDto);
        meal.setUser(user);
        mealRepository.save(meal);
        log.info("Adding new meal report to db: {}",meal.getMealType());
        return mealMapper.toDto(meal);
    }
}
