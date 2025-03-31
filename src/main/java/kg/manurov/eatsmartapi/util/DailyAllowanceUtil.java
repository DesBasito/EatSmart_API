package kg.manurov.eatsmartapi.util;

import kg.manurov.eatsmartapi.enums.ActivityLevel;
import kg.manurov.eatsmartapi.enums.Gender;
import kg.manurov.eatsmartapi.enums.StatusOfAllowance;
import kg.manurov.eatsmartapi.models.User;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@UtilityClass
public class DailyAllowanceUtil {

    public BigDecimal calculateTotalCalories(User user) {
        double bmr;
        if (Objects.equals(user.getGender(), Gender.MALE.getDescription())) {
            bmr =  88.36 + (13.4 * user.getWeight()) + (4.8 * user.getHeight()) - (5.7 * user.getAge());
        } else {
            bmr = 447.6 + (9.2 * user.getWeight()) + (3.1 * user.getHeight()) - (4.3 * user.getAge());
        }
        double activity = ActivityLevel.getFactor(user.getActivityLevel());
        return BigDecimal.valueOf(bmr * activity).setScale(2, RoundingMode.HALF_UP);
    }

    public String statusOfAllowance(BigDecimal normalValue, BigDecimal actualValue){
        if(normalValue.compareTo(actualValue) == 0) {
            return StatusOfAllowance.NORMAL.getDescription();
        } else if (normalValue.compareTo(actualValue) < 0) {
            return StatusOfAllowance.EXCESS.getDescription();
        } else {
            return StatusOfAllowance.DEFICIT.getDescription();
        }
    }
}
