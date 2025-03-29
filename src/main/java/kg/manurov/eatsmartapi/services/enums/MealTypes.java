package kg.manurov.eatsmartapi.services.enums;

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

    public static MealTypes fromString(String value) {
        for (MealTypes type : MealTypes.values()) {
            if (type.description.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Неизвестное значение цели: " + value);
    }
}
