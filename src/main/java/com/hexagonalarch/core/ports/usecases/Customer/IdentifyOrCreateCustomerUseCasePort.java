package com.hexagonalarch.core.ports.usecases.Customer;

import com.hexagonalarch.core.domain.Customer;

public interface IdentifyOrCreateCustomerUseCasePort {
    Customer identifyOrCreateCustomer(Customer c);
}
