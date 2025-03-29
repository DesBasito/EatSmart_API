package kg.manurov.eatsmartapi.mappers;

import kg.manurov.eatsmartapi.dto.UserDto;
import kg.manurov.eatsmartapi.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDto userDto);

    UserDto toDto(User user);
}
