package kg.manurov.eatsmartapi.mappers;

import kg.manurov.eatsmartapi.dto.MealDto;
import kg.manurov.eatsmartapi.enums.EnumInterface;
import kg.manurov.eatsmartapi.enums.MealTypes;
import kg.manurov.eatsmartapi.models.Meal;
import kg.manurov.eatsmartapi.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;

@Mapper(componentModel = "spring",uses = {DishMapper.class},
        imports = {MealTypes.class, LocalDate.class, EnumInterface.class}
)
public interface MealMapper {
    @Mapping(target = "dishes", source = "dishes")
    @Mapping(target = "mealType", expression = "java(EnumInterface.fromString(MealTypes.class,meal.getMealType()))")
    MealDto toDto(Meal meal);

    @Mapping(target = "date",expression = "java(LocalDate.now())")
    @Mapping(target = "mealType", expression = "java(EnumInterface.getType(MealTypes.class,mealDto.mealType()))")
    Meal toEntity(MealDto mealDto);

}
