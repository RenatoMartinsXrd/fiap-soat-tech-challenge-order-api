package com.hexagonalarch.core.usecases.validations;

public interface Validator<T> {
    ValidationResult validate(T entity);
}
