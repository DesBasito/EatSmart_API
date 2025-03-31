package kg.manurov.eatsmartapi.enums;

import lombok.Getter;

@Getter
public enum Gender implements EnumInterface {
    MALE("Мужчина"),
    TRANS("Десептикон"),
    FEMALE("Женщина");

    private final String description;

    Gender(String description) {
        this.description = description;
    }
}
