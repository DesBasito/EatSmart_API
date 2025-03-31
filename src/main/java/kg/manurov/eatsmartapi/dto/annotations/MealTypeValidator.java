package kg.manurov.eatsmartapi.dto.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kg.manurov.eatsmartapi.dto.MealDto;
import kg.manurov.eatsmartapi.dto.UserDto;
import kg.manurov.eatsmartapi.enums.*;
import kg.manurov.eatsmartapi.services.interfaces.MealService;
import org.springframework.beans.factory.annotation.Autowired;

import static kg.manurov.eatsmartapi.enums.EnumInterface.getEnumDescription;

public class MealTypeValidator implements ConstraintValidator<ValidMealType, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean isValid = true;

        context.disableDefaultConstraintViolation();

        if (Boolean.FALSE.equals(EnumInterface.isExists(MealTypes.class,value))) {
            context.buildConstraintViolationWithTemplate(getEnumDescription(MealTypes.class))
                    .addPropertyNode("email")
                    .addConstraintViolation();
            isValid = false;
        }

        return isValid;
    }
}
