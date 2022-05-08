package com.example.germes.entity;

public enum CargoType {
    FURNITURE("Мебель"),
    TIMBER("Лесоматериалы"),
    CEMENT("Цемент"),
    GRAIN("Зерно"),
    METAL_CONSTRUCTION("Метоллоконструкциия"),
    EQUIPMENT("Аппаратура"),
    FOOD("Продовольствие"),
    CLOTHING("Одежда");

    private final String displayValue;

    CargoType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
