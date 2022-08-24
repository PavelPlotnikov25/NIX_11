package com.model.phone;

public enum Manufacturer {
    SONY("Sony"),
    SAMSUNG("Samsung"),
    APPLE("Apple"),
    NOKIA("Nokia"),
    XIAOMI("Xiaomi");

    final String value;
    Manufacturer(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
