package kg.manurov.eatSmartApi.enums;

public enum MealTypes {
    BREAKFAST("завтрак"),
    LUNCH("обед"),
    DINNER("ужин"),
    SNACK("перекус");

    private final String description;

    MealTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
