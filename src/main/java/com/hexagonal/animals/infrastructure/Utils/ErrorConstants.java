package com.hexagonal.animals.infrastructure.Utils;

public enum ErrorConstants {

    ANIMAL_NOT_FOUND("Animal not found"),
    INVALID_ANIMAL_DATA("Invalid animal data"),
    NOT_UPDATED("Animal not updated"),
    DATABASE_ERROR("Database error occurred");

    private final String message;

    ErrorConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
