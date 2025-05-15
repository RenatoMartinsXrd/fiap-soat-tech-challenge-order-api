package com.hexagonalarch.core.ports.gateways;

import com.hexagonalarch.core.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerGatewayPort {

    Customer save(Customer product);

    Optional<Customer> findById(Long id);

    List<Customer> findAll();

    Optional<Customer> findByCpf(String cpf);

    Optional<Customer> findByEmail(String email);

}