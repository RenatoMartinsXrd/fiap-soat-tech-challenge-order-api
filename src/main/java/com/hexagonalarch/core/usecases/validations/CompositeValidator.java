package com.hexagonalarch.core.usecases.validations;

import java.util.List;

public class CompositeValidator<T> implements Validator<T> {
    private final List<Validator<T>> validators;

    public CompositeValidator(List<Validator<T>> validators) {
        this.validators = validators;
    }

    @Override
    public ValidationResult validate(T entity) {
        for (Validator<T> validator : validators) {
            ValidationResult result = validator.validate(entity);
            if (!result.isValid()) {
                return result;
            }
        }
        return new ValidationResult(true, null);
    }
}
