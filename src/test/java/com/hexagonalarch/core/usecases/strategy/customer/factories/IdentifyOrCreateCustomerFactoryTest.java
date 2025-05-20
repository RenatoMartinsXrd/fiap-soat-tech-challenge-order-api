package com.hexagonalarch.core.usecases.strategy.customer.factories;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.ports.gateways.CustomerGatewayPort;
import com.hexagonalarch.core.usecases.strategy.customer.CustomerStrategy;
import com.hexagonalarch.core.usecases.strategy.customer.strategies.CreateOrIdentifyCustomerCpfStrategy;
import com.hexagonalarch.core.usecases.strategy.customer.strategies.CreateOrIdentifyCustomerWithoutCpfStrategy;
import com.hexagonalarch.core.usecases.strategy.customer.strategies.DefaultCustomerStrategy;
import com.hexagonalarch.shared.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

class IdentifyOrCreateCustomerFactoryTest {

    private CustomerGatewayPort customerGatewayPort;
    private IdentifyOrCreateCustomerFactory factory;

    @BeforeEach
    void setUp() {
        customerGatewayPort = mock(CustomerGatewayPort.class);
        factory = new IdentifyOrCreateCustomerFactory(customerGatewayPort);
    }

    @Test
    void shouldReturnCpfStrategyWhenCpfIsValid() {
        Customer customer = new Customer();
        customer.setCpf("123.456.789-00");
        customer.setEmail("valid@email.com");
        customer.setName("Daniel");

        CustomerStrategy strategy = factory.getStrategy(customer);

        assertThat(strategy).isInstanceOf(CreateOrIdentifyCustomerCpfStrategy.class);
    }

    @Test
    void shouldReturnWithoutCpfStrategyWhenEmailAndNameValidButCpfInvalid() {
        Customer customer = new Customer();
        customer.setCpf("");
        customer.setEmail("valid@email.com");
        customer.setName("Daniel");

        CustomerStrategy strategy = factory.getStrategy(customer);

        assertThat(strategy).isInstanceOf(CreateOrIdentifyCustomerWithoutCpfStrategy.class);
    }

    @Test
    void shouldReturnDefaultStrategyWhenOnlyCpfIsInvalidAndMissingFields() {
        Customer customer = new Customer();
        customer.setCpf("");
        customer.setEmail("x@x.com");
        customer.setName(null);

        CustomerStrategy strategy = factory.getStrategy(customer);

        assertThat(strategy).isInstanceOf(DefaultCustomerStrategy.class);
    }

    @Test
    void shouldThrowBusinessExceptionWhenAllFieldsAreNull() {
        Customer customer = new Customer();
        customer.setCpf(null);
        customer.setEmail(null);
        customer.setName(null);

        assertThatThrownBy(() -> factory.getStrategy(customer))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Cliente não identificado, dados estão nulos");
    }
}
