package com.hexagonalarch.core.usecases.strategy.customer.strategies;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.ports.gateways.CustomerGatewayPort;
import com.hexagonalarch.core.usecases.strategy.NavigationResult;
import com.hexagonalarch.core.usecases.strategy.customer.CustomerStrategy;

import java.util.Optional;

public class CreateOrIdentifyCustomerCpfStrategy implements CustomerStrategy {
    private final CustomerGatewayPort customerRepository;

    public CreateOrIdentifyCustomerCpfStrategy(CustomerGatewayPort customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public NavigationResult<Customer> execute(Customer c) {
        Optional<Customer> byCpf = customerRepository.findByCpf((c.getCpf()));
        if(byCpf.isPresent()){
            return NavigationResult.success(byCpf.get(), "Cliente identificado");
        }
        Customer save = customerRepository.save(c);
        return NavigationResult.success(save, "Cliente cadastrado pelo nome e email");
    }
}
