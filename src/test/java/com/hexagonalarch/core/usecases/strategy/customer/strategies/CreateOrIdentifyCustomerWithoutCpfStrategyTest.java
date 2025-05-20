package com.hexagonalarch.core.usecases.strategy.customer.strategies;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.ports.gateways.CustomerGatewayPort;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CreateOrIdentifyCustomerWithoutCpfStrategyTest {

    @Test
    void shouldCallFindByEmailAndReturnCustomer() {
        CustomerGatewayPort gateway = mock(CustomerGatewayPort.class);
        Customer input = new Customer();
        input.setEmail("email@test.com");

        CreateOrIdentifyCustomerWithoutCpfStrategy strategy = new CreateOrIdentifyCustomerWithoutCpfStrategy(gateway);
        strategy.execute(input);

        verify(gateway).findByEmail("email@test.com");
    }
}
