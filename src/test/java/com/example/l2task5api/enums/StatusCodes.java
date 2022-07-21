package com.example.l2task5api.enums;

public enum StatusCodes {
    OK(200),
    CREATED(201),
    NOT_FOUND(404);

    private final int value;

    StatusCodes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
