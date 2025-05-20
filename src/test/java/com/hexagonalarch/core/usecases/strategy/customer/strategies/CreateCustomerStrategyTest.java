package com.hexagonalarch.core.usecases.strategy.customer.strategies;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.ports.gateways.CustomerGatewayPort;
import com.hexagonalarch.core.usecases.strategy.NavigationResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CreateCustomerStrategyTest {

    @Test
    void shouldCallSaveAndReturnCreatedCustomer() {
        CustomerGatewayPort gateway = mock(CustomerGatewayPort.class);
        Customer input = new Customer();
        input.setName("New");

        when(gateway.save(input)).thenReturn(input);

        CreateCustomerStrategy strategy = new CreateCustomerStrategy(gateway);
        NavigationResult<Customer> result = strategy.execute(input);

        verify(gateway).save(input);
        assertThat(result.getResult()).isEqualTo(input);
        assertThat(result.getValidationResult().isValid()).isTrue();
    }
}
