package kg.manurov.eatsmartapi.enums;

public enum GoalType implements EnumInterface {
    LOSE_WEIGHT("похудение"),
    GAIN_MUSCLE("набор массы"),
    MAINTAIN("поддержание");

    private final String description;

    GoalType(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public static Boolean isExists(String value) {
        for (GoalType goal : GoalType.values()) {
            if (goal.description.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
