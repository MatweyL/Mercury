package com.example.germes.entity;

public enum CarType {
    CONTAINER("Контейнеровоз", 20),
    VAN("Фургон",10),
    GAZELLE("Газель", 15),
    OPEN_SIDE_PLATFORM("Открытая бортовая платформа", 17),
    REFRIGERATOR("Рефрижератор", 19),
    SIDE_PLATFORM_WITH_AWNING("Бортовая платформа с тентом", 22),
    LOGGING_TRUCK("Лесовоз", 25),
    DUMP_TRUCK("Самосвал", 50),
    CEMENT_TRUCK("Цементовоз",45),
    GRAIN_CARRIER("Зерновоз",35);

    private final String displayValue;
    private final int costPerKm;

    CarType(String displayValue, int pricePerKm) {
        this.displayValue = displayValue;
        this.costPerKm = pricePerKm;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public int getCostPerKm() {
        return costPerKm;
    }

}
