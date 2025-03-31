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

    public static double getFactor(String value){
        for (ActivityLevel type : ActivityLevel.values()) {
            if (type.toString().equalsIgnoreCase(value)) {
                return type.factor;
            }
        }
        throw new IllegalArgumentException(String.format("Тип %s не найден",value));
    }
}