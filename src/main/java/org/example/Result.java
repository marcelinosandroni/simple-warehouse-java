package org.example;

public class Result<Type> {
    Type value;
    Exception error;
    boolean isSuccess;
    boolean isFailure;

    private Result(Type value, Exception error) {
        this.value = value;
        this.error = error;
        this.isSuccess = value != null;
        this.isFailure = this.isSuccess;
    }

    private Result(Type value) {
        this.value = value;
        this.isSuccess = true;
        this.isFailure = false;
    }

    private Result(Exception error) {
        this.error = error;
        this.isFailure = true;
        this.isSuccess = false;
    }

    static public <Type> Result<Type> success(Type value) {
        if (value == null) {
            throw new IllegalArgumentException("Can't create a success result with null value");
        }
        return new Result(value);
    }

    static public <Type> Result<Type> failure(Exception error) {
        if (error == null) {
            throw new IllegalArgumentException("Can't create a failure result with null error");
        }
        return new Result(error);
    }

    static public <Type> Result<Type> from(Type value) throws Exception {
        if (value == null) {
            return Result.failure(new Exception("Value is null"));
        }
        return Result.success(value);
    }
}
