package kg.manurov.eatsmartapi.services.interfaces;

import kg.manurov.eatsmartapi.dto.UserDto;
import kg.manurov.eatsmartapi.dto.UserReportDto;

import java.time.LocalDate;

public interface UserService {
    UserDto create(UserDto userDto);

    Boolean checkUniqueEmail(String email);

    UserReportDto getUserReport(Long id, LocalDate date);
}
