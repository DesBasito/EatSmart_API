package kg.manurov.eatsmartapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import kg.manurov.eatsmartapi.dto.UserDto;
import kg.manurov.eatsmartapi.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @Operation(
            summary = "Создание пользователя",
            description = "Этот эндпоинт создает нового пользователя и возвращает его данные в виде `UserDto`."
    )
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto){
        UserDto user = userService.create(userDto);
        return ResponseEntity.ok(user);
    }
}
