package kg.manurov.eatsmartapi.services.interfaces;

import kg.manurov.eatsmartapi.dto.UserDto;
import kg.manurov.eatsmartapi.models.User;

public interface UserService {
    UserDto create(UserDto userDto);

    UserDto getUserDtoById(Long userId);

    Boolean checkUniqueEmail(String email);

    User getUserById(Long userId);
}
