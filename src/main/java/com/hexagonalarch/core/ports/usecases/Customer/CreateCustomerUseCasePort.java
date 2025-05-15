package com.hexagonalarch.core.ports.usecases.Customer;

import com.hexagonalarch.core.domain.Customer;

public interface CreateCustomerUseCasePort {

    Customer createCustomer(Customer customer);

}
