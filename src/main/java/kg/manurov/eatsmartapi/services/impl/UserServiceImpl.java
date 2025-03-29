package kg.manurov.eatsmartapi.services.impl;

import kg.manurov.eatsmartapi.dto.UserDto;
import kg.manurov.eatsmartapi.mappers.UserMapper;
import kg.manurov.eatsmartapi.models.GoalType;
import kg.manurov.eatsmartapi.models.User;
import kg.manurov.eatsmartapi.repositories.GoalTypeRepository;
import kg.manurov.eatsmartapi.repositories.UserRepository;
import kg.manurov.eatsmartapi.services.enums.GoalTypes;
import kg.manurov.eatsmartapi.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;
    private final GoalTypeRepository goalTypeRepository;

//    @Autowired
//    public UserServiceImpl(UserMapper userMapper){
//        this.userMapper = userMapper;
//    }
    @Override
    public UserDto create(UserDto userDto) {
        String goalType = GoalTypes.fromString(userDto.goal());

        GoalType goal = goalTypeRepository
                .findByName(goalType)
                .orElseThrow(NoSuchElementException::new);

        User user = userMapper.toEntity(userDto);
        user.setGoalType(goal);
        repository.save(user);

        return userMapper.toDto(user);
    }
}
