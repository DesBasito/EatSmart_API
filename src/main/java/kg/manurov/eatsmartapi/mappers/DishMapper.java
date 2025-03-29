package kg.manurov.eatsmartapi.mappers;

import kg.manurov.eatsmartapi.dto.DishDto;
import kg.manurov.eatsmartapi.models.Dish;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DishMapper {
    DishDto toDto(Dish dish);
    Dish toEntity(DishDto dishDto);
}
