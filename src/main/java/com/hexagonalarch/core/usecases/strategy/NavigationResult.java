package com.hexagonalarch.core.usecases.strategy;

import com.hexagonalarch.core.usecases.validations.ValidationResult;

public class NavigationResult<T> {
    private final ValidationResult validationResult;
    private final T result;

    private NavigationResult(ValidationResult validationResult, T result) {
        this.validationResult = validationResult;
        this.result = result;
    }

    public static <T> NavigationResult<T> success(T result, String message) {
        return new NavigationResult<>(new ValidationResult(true, message), result);
    }

    public static <T> NavigationResult<T> failure(String message) {
        return new NavigationResult<>(new ValidationResult(false, message), null);
    }

    public ValidationResult getValidationResult() {
        return validationResult;
    }

    public T getResult() {
        return result;
    }
}