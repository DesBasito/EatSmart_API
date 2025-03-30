package kg.manurov.eatsmartapi.mappers;

import kg.manurov.eatsmartapi.dto.UserDto;
import kg.manurov.eatsmartapi.enums.ActivityLevel;
import kg.manurov.eatsmartapi.enums.Gender;
import kg.manurov.eatsmartapi.enums.GoalType;
import kg.manurov.eatsmartapi.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDto userDto);
    UserDto toDto(User user);
}
