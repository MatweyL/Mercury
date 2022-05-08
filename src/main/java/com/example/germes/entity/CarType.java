package com.example.germes.entity;

public enum CarType {
    CONTAINER("Контейнеровоз"),
    VAN("Фургон"),
    GAZELLE("Газель"),
    OPEN_SIDE_PLATFORM("Открытая бортовая платформа"),
    REFRIGERATOR("Рефрижератор"),
    SIDE_PLATFORM_WITH_AWNING("Бортовая платформа с тентом"),
    LOGGING_TRUCK("Лесовоз"),
    DUMP_TRUCK("Самосвал"),
    CEMENT_TRUCK("Цементовоз"),
    GRAIN_CARRIER("Зерновоз");

    private final String displayValue;

    CarType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

}
