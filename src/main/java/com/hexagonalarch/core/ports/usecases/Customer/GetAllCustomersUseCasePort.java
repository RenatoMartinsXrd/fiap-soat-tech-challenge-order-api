package com.hexagonalarch.core.ports.usecases.Customer;

import com.hexagonalarch.core.domain.Customer;

import java.util.List;

public interface GetAllCustomersUseCasePort {

    List<Customer> getAllCustomers();

}
