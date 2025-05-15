package com.hexagonalarch.core.usecases.strategy.customer.strategies;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.usecases.strategy.NavigationResult;
import com.hexagonalarch.core.usecases.strategy.customer.CustomerStrategy;

public class DefaultCustomerStrategy implements CustomerStrategy {
    @Override
    public NavigationResult execute(Customer customer) {
        return NavigationResult.failure("Nenhuma estratégia aplicável para o cliente.");
    }
}