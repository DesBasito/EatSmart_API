package kg.manurov.eatsmartapi.enums;

import lombok.Getter;

@Getter
public enum GoalType implements EnumInterface {
    LOSE_WEIGHT("похудение"),
    GAIN_MUSCLE("набор массы"),
    MAINTAIN("поддержание");

    private final String description;

    GoalType(String description) {
        this.description = description;
    }
}
