package ru.vsu.demyanov.models;

import ru.vsu.demyanov.view.resources.Strings;

public enum ErrorType {
    ALREADY_EXISTS(Strings.Error.ALREADY_EXISTS),
    INVALID_INPUT(Strings.Error.INVALID_INPUT),
    NOT_FOUND(Strings.Error.NOT_FOUND),
    SQL_ERROR(Strings.Error.SQL_ERROR);

    private final String message;

    ErrorType(String code) {
        this.message = code;
    }

    public String getMessage() {
        return message;
    }
}
