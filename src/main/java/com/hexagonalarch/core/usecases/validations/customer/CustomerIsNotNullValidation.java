package com.hexagonalarch.core.usecases.validations.customer;

import com.hexagonalarch.core.domain.Customer;
import com.hexagonalarch.core.usecases.validations.ValidationResult;
import com.hexagonalarch.core.usecases.validations.Validator;

public class CustomerIsNotNullValidation implements Validator<Customer> {
    @Override
    public ValidationResult validate(Customer customer) {
        boolean cpfIsEmpty = customer == null || customer.getCpf() == null || customer.getCpf().isEmpty();
        boolean emailIsEmpty = customer == null || customer.getEmail() == null || customer.getEmail().isEmpty();
        boolean nameIsEmpty = customer == null || customer.getName() == null || customer.getName().isEmpty();

        if (cpfIsEmpty && emailIsEmpty && nameIsEmpty) {
            return new ValidationResult(false, "Cliente não identificado, dados estão nulos");
        }

        return new ValidationResult(true, null);
    }
}