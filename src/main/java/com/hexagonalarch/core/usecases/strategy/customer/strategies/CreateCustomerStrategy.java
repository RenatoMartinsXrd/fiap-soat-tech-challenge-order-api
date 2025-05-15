package com.hexagonalarch.core.usecases.strategy.customer.strategies;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.ports.gateways.CustomerGatewayPort;
import com.hexagonalarch.core.usecases.strategy.NavigationResult;
import com.hexagonalarch.core.usecases.strategy.customer.CustomerStrategy;
import com.hexagonalarch.core.usecases.validations.factory.CustomerValidationFactory;

public class CreateCustomerStrategy implements CustomerStrategy {
    private final CustomerGatewayPort customerRepository;

    public CreateCustomerStrategy(CustomerGatewayPort customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public NavigationResult<Customer> execute(Customer customer) {
        if (!CustomerValidationFactory.getCustomerNotNullValidation().validate(customer).isValid()) {
            Customer save = customerRepository.save(customer);
            return NavigationResult.success(save, "Cliente identificado");
        }
        return NavigationResult.failure("Cliente identificado");
    }
}
