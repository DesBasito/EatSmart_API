package kg.manurov.eatsmartapi.enums;

public enum Gender implements EnumInterface {
    MALE("Мужчина"),
    TRANS("Десептикон"),
    FEMALE("Женщина");

    private final String description;

    Gender(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }
    public static Boolean isExists(String value) {
        for (Gender type : Gender.values()) {
            if (type.description.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
