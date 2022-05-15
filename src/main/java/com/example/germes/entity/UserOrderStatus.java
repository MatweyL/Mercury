package com.example.germes.entity;

public enum UserOrderStatus {
    NOT_ASSIGNED("Не назначен"),
    ASSIGNED("Назначен"),
    TRANSPORTED("Транспортируется"),
    DELIVERED("Доставлен");

    private final String displayValue;

    UserOrderStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
