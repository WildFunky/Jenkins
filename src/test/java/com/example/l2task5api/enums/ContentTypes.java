package com.example.l2task5api.enums;

public enum ContentTypes {
    JSON("application/json; charset=utf-8");

    private final String value;

    ContentTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
