package com.hexagonalarch.core.usecases.strategy.customer.strategies;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.ports.gateways.CustomerGatewayPort;
import com.hexagonalarch.core.usecases.strategy.NavigationResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CreateCustomerStrategyTest {

    @Test
    void shouldCallSaveAndReturnCreatedCustomerWhenCustomerIsInvalid() {
        CustomerGatewayPort gateway = mock(CustomerGatewayPort.class);
        Customer input = new Customer();

        when(gateway.save(any(Customer.class))).thenReturn(input);

        CreateCustomerStrategy strategy = new CreateCustomerStrategy(gateway);
        NavigationResult<Customer> result = strategy.execute(input);

        verify(gateway).save(any(Customer.class));
        assertThat(result.getResult()).isEqualTo(input);
        assertThat(result.getValidationResult().isValid()).isTrue();
    }

}
