package kg.manurov.eatsmartapi.dto.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MealTypeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMealType {
    String message() default "Data not valid!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
