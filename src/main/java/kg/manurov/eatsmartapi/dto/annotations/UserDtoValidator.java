package kg.manurov.eatsmartapi.dto.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kg.manurov.eatsmartapi.dto.UserDto;
import kg.manurov.eatsmartapi.enums.ActivityLevel;
import kg.manurov.eatsmartapi.enums.EnumInterface;
import kg.manurov.eatsmartapi.enums.Gender;
import kg.manurov.eatsmartapi.enums.GoalType;
import kg.manurov.eatsmartapi.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDtoValidator implements ConstraintValidator<ValidUserDto, UserDto> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(UserDto value, ConstraintValidatorContext context) {
        boolean isValid = true;

        context.disableDefaultConstraintViolation();

        if (Boolean.FALSE.equals(checkEmail(value.email()))) {
            context.buildConstraintViolationWithTemplate("Email in use")
                    .addPropertyNode("email")
                    .addConstraintViolation();
            isValid = false;
        }

        if (Boolean.FALSE.equals(checkGoal(value.goal()))) {
            context.buildConstraintViolationWithTemplate(getEnumDescription(GoalType.class))
                    .addPropertyNode("goal")
                    .addConstraintViolation();
            isValid = false;
        }

        if (Boolean.FALSE.equals(checkActivity(value.activityLevel()))) {
            context.buildConstraintViolationWithTemplate(getEnumDescription(ActivityLevel.class))
                    .addPropertyNode("activityLevel")
                    .addConstraintViolation();
            isValid = false;
        }

        if (Boolean.FALSE.equals(checkGender(value.gender()))) {
            context.buildConstraintViolationWithTemplate(getEnumDescription(Gender.class))
                    .addPropertyNode("gender")
                    .addConstraintViolation();
            isValid = false;
        }

        return isValid;
    }

    private Boolean checkEmail(String email){
        return email != null && Boolean.FALSE.equals(userService.checkUniqueEmail(email));
    }

    private Boolean checkGoal(String goal){
        return goal != null && Boolean.TRUE.equals(GoalType.isExists(goal));
    }

    private Boolean checkGender(String gender){
        return gender != null && Boolean.TRUE.equals(Gender.isExists(gender));
    }

    private Boolean checkActivity(String activity){
        return activity != null && Boolean.TRUE.equals(ActivityLevel.isExists(activity));
    }

    private <E extends Enum<E> & EnumInterface> String getEnumDescription(Class<E> enumClass) {
        StringBuilder str = new StringBuilder();
        str.append("Указан неверный тип. Укажите одну из: ");
        for (E enumValue : enumClass.getEnumConstants()) {
            str.append(String.format("-> %s ",enumValue.getDescription()));
        }
        return str.toString();
    }

}
