package kg.manurov.eatsmartapi.serviceTest;

import kg.manurov.eatsmartapi.dto.DishDto;
import kg.manurov.eatsmartapi.mappers.DishMapper;
import kg.manurov.eatsmartapi.models.Dish;
import kg.manurov.eatsmartapi.repositories.DishRepository;
import kg.manurov.eatsmartapi.services.impl.DishServiceImpl;
import kg.manurov.eatsmartapi.services.interfaces.DishService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DishServiceTest {

    private static final Long NEW_GENERATED_ID = 1L;

    @Mock
    private DishRepository repository;

    @Mock
    private DishMapper mapper;

    @InjectMocks
    private DishServiceImpl dishService;


    @Test
    void checkNewDishDtoIdRight() {
        DishDto dishDto = new DishDto(0L,"new dish", 205.66, 56.3, 66.5, 78.4);
        Dish dish = Dish.builder().id(0L)
                .name(dishDto.name())
                .calories(dishDto.calories())
                .fats(dishDto.fats())
                .carbohydrates(dishDto.carbohydrates())
                .build();
        when(mapper.toEntity(dishDto)).thenReturn(dish);
        when(repository.save(dish)).thenAnswer(invocation -> {
            dish.setId(NEW_GENERATED_ID);
            return dish;
        });
        when(mapper.toDto(dish)).thenReturn(new DishDto(NEW_GENERATED_ID, dishDto.name(),
                dishDto.calories(), dishDto.fats(), dishDto.carbohydrates(), dishDto.protein()));

        Long id = dishService.create(dishDto).id();

        assertEquals(NEW_GENERATED_ID, id);
    }

    @Test
    void getNewDishDtoId() {
        DishDto dishDto = new DishDto(0L,null, null, 56.3, 66.5, 78.4);
        Dish dish = Dish.builder().id(0L)
                .name(dishDto.name())
                .calories(dishDto.calories())
                .fats(dishDto.fats())
                .carbohydrates(dishDto.carbohydrates())
                .build();
        when(mapper.toEntity(dishDto)).thenReturn(dish);
        when(repository.save(dish)).thenAnswer(invocation -> {
            throw new IllegalArgumentException();
        });

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            dishService.create(dishDto);
        });

        assertNotNull(exception);
    }

}
