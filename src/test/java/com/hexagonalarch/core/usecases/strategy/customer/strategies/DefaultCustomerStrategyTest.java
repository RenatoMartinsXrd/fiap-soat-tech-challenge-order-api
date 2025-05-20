package com.hexagonalarch.core.usecases.strategy.customer.strategies;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.usecases.strategy.NavigationResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultCustomerStrategyTest {

    @Test
    void shouldReturnFailureWhenNoStrategyApplies() {
        Customer input = new Customer();
        input.setName("Fallback");

        DefaultCustomerStrategy strategy = new DefaultCustomerStrategy();
        NavigationResult<Customer> result = strategy.execute(input);

        assertThat(result.getResult()).isNull();
        assertThat(result.getValidationResult().isValid()).isFalse();
        assertThat(result.getValidationResult().getMessage()).isEqualTo("Nenhuma estratégia aplicável para o cliente.");
    }
}
