package kg.manurov.eatsmartapi.util;

import kg.manurov.eatsmartapi.enums.ActivityLevel;
import kg.manurov.eatsmartapi.enums.Gender;
import kg.manurov.eatsmartapi.models.User;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;

@UtilityClass
public class CalculateUtil {

    public BigDecimal calculateTotalCalories(User user, ActivityLevel activityLevel) {
        double bmr = calculateBMR(user);
        return BigDecimal.valueOf(bmr * activityLevel.getFactor()).setScale(2, RoundingMode.HALF_UP);
    }
    public double calculateBMR(User user) {
        if (user.getGender() == Gender.MALE.getDescription()) {
            return 88.36 + (13.4 * user.getWeight()) + (4.8 * user.getHeight()) - (5.7 * user.getAge());
        } else {
            return 447.6 + (9.2 * user.getWeight()) + (3.1 * user.getHeight()) - (4.3 * user.getAge());
        }
    }
}
