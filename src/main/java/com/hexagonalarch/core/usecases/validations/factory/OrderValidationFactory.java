package com.hexagonalarch.core.usecases.validations.factory;

import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.usecases.validations.CompositeValidator;
import com.hexagonalarch.core.usecases.validations.Validator;
import com.hexagonalarch.core.usecases.validations.order.MinimalProductQuantityValidation;

import java.util.List;

public class OrderValidationFactory {

    public static Validator<Order> getValidatorsForCreateOrder() {
        return new CompositeValidator<>(List.of(
                new MinimalProductQuantityValidation()
        ));
    }
}
