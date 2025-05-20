package com.hexagonalarch.core.usecases.strategy.customer.strategies;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.ports.gateways.CustomerGatewayPort;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CreateOrIdentifyCustomerCpfStrategyTest {

    @Test
    void shouldCallFindByCpfAndReturnCustomer() {
        CustomerGatewayPort gateway = mock(CustomerGatewayPort.class);
        Customer input = new Customer();
        input.setCpf("123.456.789-00");

        CreateOrIdentifyCustomerCpfStrategy strategy = new CreateOrIdentifyCustomerCpfStrategy(gateway);
        strategy.execute(input);

        verify(gateway).findByCpf("123.456.789-00");
    }
}
