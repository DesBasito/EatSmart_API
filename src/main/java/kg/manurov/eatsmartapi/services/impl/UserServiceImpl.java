package kg.manurov.eatsmartapi.services.impl;

import kg.manurov.eatsmartapi.dto.UserDto;
import kg.manurov.eatsmartapi.dto.UserReportDto;
import kg.manurov.eatsmartapi.mappers.UserMapper;
import kg.manurov.eatsmartapi.models.Meal;
import kg.manurov.eatsmartapi.models.User;
import kg.manurov.eatsmartapi.repositories.UserRepository;
import kg.manurov.eatsmartapi.services.interfaces.MealService;
import kg.manurov.eatsmartapi.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;
    private final MealService mealService;

    @Override
    public UserDto create(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        repository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public Boolean checkUniqueEmail(String email){
        return repository.existsByEmail(email);
    }

    @Override
    public UserReportDto getUserReport(Long id, LocalDate date) {
//        User user = repository.findById(id).orElseThrow(NoSuchElementException::new);
//        List<Meal> meal = mealService.getMealByUserIdAndDate(id,date);
//        UserReportDto userReportDto = userMapper.toUserReprtDto();
        return null;
    }

    private UserReportDto calculateAndReturnUserReport(List<Meal> meals, User user){
        return null;
    }
}
