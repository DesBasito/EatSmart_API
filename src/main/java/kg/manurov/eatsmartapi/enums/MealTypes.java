package kg.manurov.eatsmartapi.enums;

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

    public static MealTypes fromString(String gender) {
        try {
            return MealTypes.valueOf(gender.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    public static Boolean checkIsMealType(String value) {
        for (MealTypes type : MealTypes.values()) {
            if (type.description.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
