package com.hexagonalarch.core.usecases.validations.order;

import com.hexagonalarch.core.domain.Order;
import com.hexagonalarch.core.domain.Product;
import com.hexagonalarch.core.usecases.validations.ValidationResult;
import com.hexagonalarch.shared.exception.BusinessException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MinimalProductQuantityValidationTest {

    private final MinimalProductQuantityValidation validation = new MinimalProductQuantityValidation();

    @Test
    void shouldReturnValidWhenOrderHasProducts() {
        Order order = new Order();
        order.setProducts(List.of(new Product()));

        ValidationResult result = validation.validate(order);

        assertThat(result.isValid()).isTrue();
        assertThat(result.getMessage()).isNull();
    }

    @Test
    void shouldThrowBusinessExceptionWhenOrderHasNoProducts() {
        Order order = new Order();
        order.setProducts(List.of());

        assertThatThrownBy(() -> validation.validate(order))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Pedido deve conter pelo menos um produto");
    }
}
