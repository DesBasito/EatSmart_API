package kg.manurov.eatsmartapi.services.impl;

import kg.manurov.eatsmartapi.dto.UserDto;
import kg.manurov.eatsmartapi.mappers.UserMapper;
import kg.manurov.eatsmartapi.models.User;
import kg.manurov.eatsmartapi.repositories.UserRepository;
import kg.manurov.eatsmartapi.services.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto create(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        repository.save(user);
        log.info("Adding new user to db: {}", user.getName());
        return userMapper.toDto(user);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public Boolean checkUniqueEmail(String email){
        return repository.existsByEmail(email);
    }

    @Override
    public User getUserById(Long userId) {
        return repository.findById(userId).orElseThrow(NoSuchElementException::new);
    }
}
