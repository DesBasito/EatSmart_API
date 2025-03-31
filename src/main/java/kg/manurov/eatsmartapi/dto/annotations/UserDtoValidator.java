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

import static kg.manurov.eatsmartapi.enums.EnumInterface.getEnumDescription;

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

        if (Boolean.FALSE.equals(EnumInterface.isExists(GoalType.class,value.goalType()))) {
            context.buildConstraintViolationWithTemplate(getEnumDescription(GoalType.class))
                    .addPropertyNode("goalType")
                    .addConstraintViolation();
            isValid = false;
        }

        if (Boolean.FALSE.equals(EnumInterface.isExists(ActivityLevel.class,value.activityLevel()))) {
            context.buildConstraintViolationWithTemplate(getEnumDescription(ActivityLevel.class))
                    .addPropertyNode("activityLevel")
                    .addConstraintViolation();
            isValid = false;
        }

        if (Boolean.FALSE.equals(EnumInterface.isExists(Gender.class,value.gender()))) {
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

}
