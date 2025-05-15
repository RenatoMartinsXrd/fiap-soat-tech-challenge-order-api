package com.hexagonalarch.core.usecases.strategy.customer;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.usecases.strategy.NavigationResult;

public interface CustomerStrategy {
    NavigationResult<Customer>  execute(Customer customer);
}
