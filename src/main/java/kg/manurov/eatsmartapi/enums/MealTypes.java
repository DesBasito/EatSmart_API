package kg.manurov.eatsmartapi.enums;

import lombok.Getter;

@Getter
public enum MealTypes implements EnumInterface{
    BREAKFAST("завтрак"),
    LUNCH("обед"),
    DINNER("ужин"),
    SNACK("перекус");

    private String description;

    MealTypes(String description) {
        this.description = description;
    }
}
