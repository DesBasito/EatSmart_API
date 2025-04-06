package kg.manurov.eatsmartapi.serviceTest;

import kg.manurov.eatsmartapi.dto.UserDto;
import kg.manurov.eatsmartapi.mappers.UserMapper;
import kg.manurov.eatsmartapi.models.User;
import kg.manurov.eatsmartapi.repositories.UserRepository;
import kg.manurov.eatsmartapi.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private static final Long NEW_USER_ID = 1L;
    @Mock
    private UserRepository repository;
    @Mock
    private UserMapper mapper;
    @InjectMocks
    private UserServiceImpl userService;


    @Test
    void checkNewUserDtoId() {
        UserDto userDto = new UserDto(0L, "John Doe", 22, 175.6,55.7,
                "qwe@qwe", "средняя","Мужчина","Набор массы");
        User user = User.builder().id(0L)
                .name(userDto.name())
                .age(userDto.age())
                .height(userDto.height())
                .weight(userDto.weight())
                .build();

        when(mapper.toEntity(userDto)).thenReturn(user);
        when(repository.save(user)).thenAnswer(invocation -> {
            user.setId(NEW_USER_ID);
            return user;
        });
        when(mapper.toDto(user)).thenReturn(
                new UserDto(NEW_USER_ID, "John Doe", 22, 175.6,55.7,
                        "qwe@qwe", "средняя","Мужчина","Набор массы")
        );

        Long id = userService.create(userDto).id();

        assertEquals(NEW_USER_ID, id);
    }

    @Test
    void checkForValidations() {
        UserDto userDto = new UserDto(0L, null, 22, null,55.7,
                "qwe@qwe", "средняя","Мужчина","Набор массы");
        User user = User.builder().id(0L)
                .name(userDto.name())
                .age(userDto.age())
                .height(userDto.height())
                .weight(userDto.weight())
                .build();

        when(mapper.toEntity(userDto)).thenReturn(user);
        when(repository.save(user)).thenAnswer(invocation -> {
            throw new IllegalArgumentException();
        });

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.create(userDto);
        });

        assertNotNull(exception);
    }
}
