package kg.manurov.eatsmartapi.enums;

import lombok.Getter;

@Getter
public enum ActivityLevel implements EnumInterface {
    SEDENTARY("Минимальная", 1.2),
    LIGHT("Легкая", 1.375),
    MODERATE("Средняя", 1.55),
    ACTIVE("Высокая", 1.725),
    VERY_ACTIVE("Спортсмен", 1.9);

    private final String description;
    private final double factor;

    ActivityLevel(String description, double factor) {
        this.description = description;
        this.factor = factor;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public static Boolean isExists(String value) {
        for (ActivityLevel type : ActivityLevel.values()) {
            if (type.description.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}