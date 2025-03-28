package kg.manurov.eatSmartApi.services.components.mappers;

import kg.manurov.eatSmartApi.dto.UserDto;
import kg.manurov.eatSmartApi.models.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public UserDto toDto(User u){
        return new UserDto(
                u.getId(),
                u.getName(),
                u.getAge(),
                u.getHeight(),
                u.getWeight(),
                u.getEmail(),
                u.getGoalType().getName()
        );
    }

    public User toEntity(UserDto u){
        return User.builder()
                .id(u.id())
                .age(u.age())
                .name(u.name())
                .email(u.email())
                .height(u.height())
                .weight(u.weight())
                .build();
    }
}
