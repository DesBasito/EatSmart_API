package kg.manurov.eatsmartapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import kg.manurov.eatsmartapi.dto.UserDto;
import kg.manurov.eatsmartapi.dto.UserReportDto;
import kg.manurov.eatsmartapi.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto userDto){
        UserDto user = userService.create(userDto);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserReportDto> getDailyAllowance(@RequestParam LocalDate date, @PathVariable Long id){
        return ResponseEntity.ok(userService.getUserReport(id,date));
    }
}
