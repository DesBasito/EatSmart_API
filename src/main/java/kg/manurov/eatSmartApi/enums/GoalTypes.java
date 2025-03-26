package kg.manurov.eatSmartApi.enums;

public enum GoalTypes {
    WEIGHT_LOSS("похудение"),
    MUSCLE_GAIN("набор массы"),
    MAINTENANCE("держать тонус");

    private final String description;

    GoalTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
