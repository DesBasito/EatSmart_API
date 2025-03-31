package kg.manurov.eatsmartapi.services.interfaces;

import kg.manurov.eatsmartapi.dto.UserDto;
import kg.manurov.eatsmartapi.models.User;

import java.util.List;

public interface UserService {
    UserDto create(UserDto userDto);

    List<User> getAllUsers();

    Boolean checkUniqueEmail(String email);

    User getUserById(Long userId);
}
