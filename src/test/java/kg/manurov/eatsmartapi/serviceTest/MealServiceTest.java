package kg.manurov.eatsmartapi.serviceTest;

import kg.manurov.eatsmartapi.dto.DishDto;
import kg.manurov.eatsmartapi.dto.MealDto;
import kg.manurov.eatsmartapi.dto.UserDto;
import kg.manurov.eatsmartapi.mappers.MealMapper;
import kg.manurov.eatsmartapi.models.Meal;
import kg.manurov.eatsmartapi.models.User;
import kg.manurov.eatsmartapi.repositories.MealRepository;
import kg.manurov.eatsmartapi.services.impl.MealServiceImpl;
import kg.manurov.eatsmartapi.services.interfaces.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MealServiceTest {
    private static final Long NEW_MEAL_ID = 3L;
    private List<Meal> meals;
    private User user;
    private LocalDate date;
    private List<MealDto> mealDtoList;
    @Mock
    private MealRepository repository;
    @Mock
    private MealMapper mapper;
    @Mock
    private UserService userService;

    @InjectMocks
    private MealServiceImpl mealService;

    @BeforeEach
    void setUpMeanings(){
        user = User.builder().id(1L)
                .name("Hello there")
                .age(22)
                .height(177.3)
                .weight(69.7)
                .build();
        date = LocalDate.now();

        Meal meal1 = new Meal();
        meal1.setId(1L);
        meal1.setMealType("Breakfast");
        meal1.setUser(user);
        meal1.setDate(date);

        Meal meal2 = new Meal();
        meal2.setId(2L);
        meal2.setMealType("Lunch");
        meal1.setUser(user);
        meal1.setDate(date);

        meals = List.of(meal1, meal2);

        MealDto dto1 = new MealDto(1L, meal1.getMealType(), new ArrayList<>());
        MealDto dto2 = new MealDto(2L, meal2.getMealType(), new ArrayList<>());
        mealDtoList = List.of(dto1, dto2);
    }

    @Test
    void shouldReturnMealsWhenInvokeMeals() {
        when(repository.getMealsByUser_IdAndDate(user.getId(), date)).thenReturn(meals);
        when(mapper.toDto(meals.get(0))).thenReturn(mealDtoList.get(0));
        when(mapper.toDto(meals.get(1))).thenReturn(mealDtoList.get(1));
        List<MealDto> result = mealService.getMealsByUserIdAndDate(user.getId(), date);
        assertEquals(mealDtoList.size(), result.size());
        assertEquals(mealDtoList.get(0).mealType(),result.get(0).mealType());
        assertEquals(mealDtoList.get(1).mealType(),result.get(1).mealType());
    }

    @Test
    void addNewMealType(){
        MealDto mealDto = new MealDto(null,"Breakfast",new ArrayList<DishDto>());
        Meal meal = new Meal();
        meal.setMealType(mealDto.mealType());
        meal.setDate(date);
        meal.setUser(user);
        when(mapper.toEntity(mealDto)).thenReturn(meal);
        when(repository.save(meal)).thenAnswer(invocation->{
            meal.setId(NEW_MEAL_ID);
            return meal;
        });
        when(mapper.toDto(meal)).thenReturn(new MealDto(NEW_MEAL_ID,"Breakfast",new ArrayList<DishDto>()));
        Long id = mealService.create(mealDto,user.getId()).id();
        assertEquals(NEW_MEAL_ID, id);
    }

    @Test
    void throwExceptionWhileCreatingNewMeal(){
        MealDto mealDto = new MealDto(null,null,null);
        Meal meal = new Meal();
        meal.setMealType(mealDto.mealType());
        meal.setDate(date);
        meal.setUser(user);
        when(mapper.toEntity(mealDto)).thenReturn(meal);
        when(repository.save(meal)).thenThrow(new IllegalArgumentException());
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            mealService.create(mealDto, user.getId());
        });

        assertNotNull(exception);
    }

}
