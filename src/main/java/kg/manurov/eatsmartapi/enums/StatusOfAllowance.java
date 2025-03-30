package kg.manurov.eatsmartapi.enums;

public enum StatusOfAllowance {
    NORMAL("Уложился в норму"),
    DEFICIT("Не уложился (не хватило калорий)"),
    EXCESS("Не уложился (превысил норму калорий)");

    private final String description;

    StatusOfAllowance(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
