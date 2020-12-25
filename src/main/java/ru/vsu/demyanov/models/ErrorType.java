package ru.vsu.demyanov.models;

import ru.vsu.demyanov.view.resources.Strings;

public enum ErrorType {
    CONFIG_READ(Strings.Error.CONFIG),
    DATA_ERROR(Strings.Error.DATA_ERROR),
    SQL_ERROR(Strings.Error.SQL_ERROR),
    ALREADY_EXISTS(Strings.Error.ALREADY_EXISTS),
    NOT_FOUND(Strings.Error.NOT_FOUND);

    private String message;

    ErrorType(String code) {
        this.message = code;
    }

    public String getMessage() {
        return message;
    }
}
