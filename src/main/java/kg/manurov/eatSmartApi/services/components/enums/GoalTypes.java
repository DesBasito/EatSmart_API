package kg.manurov.eatSmartApi.services.components.enums;

public enum GoalTypes {
    WEIGHT_LOSS("похудение"),
    MUSCLE_GAIN("набор массы"),
    MAINTENANCE("поддержание");

    private final String description;

    GoalTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static String fromString(String value) {
        for (GoalTypes goal : GoalTypes.values()) {
            if (goal.description.equalsIgnoreCase(value)) {
                return goal.getDescription();
            }
        }
        throw new IllegalArgumentException("Неизвестное значение цели: " + value);
    }
}
