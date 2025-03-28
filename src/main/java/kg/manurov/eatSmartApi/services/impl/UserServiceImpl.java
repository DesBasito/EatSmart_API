package kg.manurov.eatSmartApi.services.impl;

import kg.manurov.eatSmartApi.dto.UserDto;
import kg.manurov.eatSmartApi.models.GoalType;
import kg.manurov.eatSmartApi.models.User;
import kg.manurov.eatSmartApi.repositories.GoalTypeRepository;
import kg.manurov.eatSmartApi.repositories.UserRepository;
import kg.manurov.eatSmartApi.services.components.enums.GoalTypes;
import kg.manurov.eatSmartApi.services.components.mappers.UserMapper;
import kg.manurov.eatSmartApi.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final GoalTypeRepository goalTypeRepository;
    @Override
    public UserDto create(UserDto userDto) {
        String goalType = GoalTypes.fromString(userDto.goal());

        GoalType goal = goalTypeRepository
                .findByName(goalType)
                .orElseThrow(NoSuchElementException::new);

        User user = UserMapper.toEntity(userDto);
        user.setGoalType(goal);
        repository.save(user);

        return UserMapper.toDto(user);
    }
}
