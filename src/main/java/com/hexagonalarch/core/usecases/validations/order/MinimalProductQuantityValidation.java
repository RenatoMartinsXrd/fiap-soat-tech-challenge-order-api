package com.hexagonalarch.core.usecases.validations.order;

import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.usecases.validations.ValidationResult;
import com.hexagonalarch.core.usecases.validations.Validator;
import com.hexagonalarch.shared.exception.BusinessException;

public class MinimalProductQuantityValidation implements Validator<Order> {

    @Override
    public ValidationResult validate(Order order) {
        if (order.getProducts().isEmpty()) {
            throw new BusinessException("Pedido deve conter pelo menos um produto");
        }

        return new ValidationResult(true, null);
    }
}

