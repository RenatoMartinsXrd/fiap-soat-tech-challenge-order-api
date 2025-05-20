package com.hexagonalarch.core.usecases.validations.factory;

import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.Product;
import com.hexagonalarch.core.usecases.validations.CompositeValidator;
import com.hexagonalarch.core.usecases.validations.ValidationResult;
import com.hexagonalarch.core.usecases.validations.Validator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrderValidationFactoryTest {

    @Test
    void shouldReturnCompositeValidatorWithMinimalProductQuantityValidation() {
        Validator<Order> validator = OrderValidationFactory.getValidatorsForCreateOrder();
        assertThat(validator).isInstanceOf(CompositeValidator.class);

        Order order = new Order();
        order.setProducts(List.of(new Product()));

        ValidationResult result = validator.validate(order);

        assertThat(result).isNotNull();
        assertThat(result.isValid()).isTrue();
    }

}
