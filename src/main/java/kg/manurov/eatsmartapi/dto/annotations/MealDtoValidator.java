package kg.manurov.eatsmartapi.dto.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kg.manurov.eatsmartapi.dto.MealDto;
import kg.manurov.eatsmartapi.services.interfaces.MealService;
import org.springframework.beans.factory.annotation.Autowired;

public class MealDtoValidator implements ConstraintValidator<ValidMealDto, MealDto> {
    @Autowired
    private MealService mealService;

    @Override
    public boolean isValid(MealDto value, ConstraintValidatorContext context) {
        return false;
    }
}
