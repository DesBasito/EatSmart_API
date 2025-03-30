package kg.manurov.eatsmartapi.dto.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserDtoValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUserDto {


    String message() default "Data not valid!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
