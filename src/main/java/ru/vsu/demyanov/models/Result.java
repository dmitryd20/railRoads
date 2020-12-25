package ru.vsu.demyanov.models;

public class Result<T> {

    private T value;
    private ErrorType error;

    public static <T> Result<T> success(T value) {
        return new Result(value);
    }

    private Result(T value) {
        this.value = value;
    }

    public static <T> Result fail(ErrorType error) {
        return new Result(error);
    }

    private Result(ErrorType error) {
        this.error = error;
    }

    public T getValue() {
        return value;
    }

    public ErrorType getError() {
        return error;
    }

    public boolean isSuccess() {
        return error == null;
    }

    public boolean isError() {
        return error != null;
    }
}
