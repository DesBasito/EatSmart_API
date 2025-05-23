package kg.manurov.eatsmartapi.enums;

import lombok.Getter;

@Getter
public enum StatusOfAllowance {
    NORMAL("Уложился в норму"),
    DEFICIT("Не уложился - не хватило калорий"),
    EXCESS("Не уложился - превысил норму калорий");

    private final String description;

    StatusOfAllowance(String description)  {
        this.description = description;
    }
}
