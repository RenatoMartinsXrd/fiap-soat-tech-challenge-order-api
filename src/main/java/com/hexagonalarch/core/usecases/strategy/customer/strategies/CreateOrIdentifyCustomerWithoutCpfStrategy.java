package com.hexagonalarch.core.usecases.strategy.customer.strategies;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.ports.gateways.CustomerGatewayPort;
import com.hexagonalarch.core.usecases.strategy.NavigationResult;
import com.hexagonalarch.core.usecases.strategy.customer.CustomerStrategy;

import java.util.Optional;

public class CreateOrIdentifyCustomerWithoutCpfStrategy implements CustomerStrategy {
    private final CustomerGatewayPort customerRepository;

    public CreateOrIdentifyCustomerWithoutCpfStrategy(CustomerGatewayPort customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public NavigationResult<Customer> execute(Customer c) {
        Optional<Customer> byEmail = customerRepository.findByEmail((c.getEmail()));
        if(byEmail.isPresent()){
            return NavigationResult.success(byEmail.get(), "Cliente identificado");
        }
        Customer save = customerRepository.save(c);
        return NavigationResult.success(save, "Cliente identificado");
    }
}
