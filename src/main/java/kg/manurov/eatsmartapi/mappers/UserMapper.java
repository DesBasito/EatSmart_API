package kg.manurov.eatsmartapi.mappers;

import kg.manurov.eatsmartapi.dto.UserDto;
import kg.manurov.eatsmartapi.enums.ActivityLevel;
import kg.manurov.eatsmartapi.enums.EnumInterface;
import kg.manurov.eatsmartapi.enums.Gender;
import kg.manurov.eatsmartapi.enums.GoalType;
import kg.manurov.eatsmartapi.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {EnumInterface.class,ActivityLevel.class, Gender.class, GoalType.class})
public interface UserMapper {
    @Mapping(target = "activityLevel", expression = "java(EnumInterface.getType(ActivityLevel.class,userDto.activityLevel()))")
    @Mapping(target = "goalType", expression = "java(EnumInterface.getType(GoalType.class,userDto.goalType()))")
    @Mapping(target = "gender", expression = "java(EnumInterface.getType(Gender.class,userDto.gender()))")
    User toEntity(UserDto userDto);

    @Mapping(target = "activityLevel", expression = "java(EnumInterface.fromString(ActivityLevel.class,user.getActivityLevel()))")
    @Mapping(target = "goalType", expression = "java(EnumInterface.fromString(GoalType.class,user.getGoalType()))")
    @Mapping(target = "gender", expression = "java(EnumInterface.fromString(Gender.class,user.getGender()))")
    UserDto toDto(User user);
}
